package bw5team1.epicenergyservices.entities.cliente;

import bw5team1.epicenergyservices.entities.comune.Comune;
import bw5team1.epicenergyservices.entities.comune.ComuneService;
import bw5team1.epicenergyservices.entities.fattura.Fattura;

import bw5team1.epicenergyservices.entities.indirizzo.Indirizzo;
import bw5team1.epicenergyservices.entities.indirizzo.IndirizzoDAO;
import bw5team1.epicenergyservices.entities.indirizzo.IndirizzoService;
import bw5team1.epicenergyservices.entities.sedeLegale.SedeLegale;
import bw5team1.epicenergyservices.entities.sedeOperativa.SedeOperativa;
import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ComuneService comuneService;

    @Autowired
    IndirizzoService indirizzoService;

    @Autowired
    IndirizzoDAO indirizzoDAO;

    // trova cliente per id
    public Cliente findById(UUID id) throws ChangeSetPersister.NotFoundException {
        return clienteRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }
    // trova cliente per id ed elimina
    public void findByIdAndDelete(UUID id) throws ChangeSetPersister.NotFoundException {
        Cliente clienteTrovato = this.findById(id);
        clienteRepository.delete(clienteTrovato);
    }

    // trova tutti i clienti ordinati
    public Page<Cliente> findAll(int page, String ordinamento) {
        Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
        return clienteRepository.findAll(pagina);
    }

    // filtro fatturazione annuale
    public Page<Cliente> filterFatturatoAnnuale(double fatturatoAnnuale, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return clienteRepository.findByFatturatoAnnuale(fatturatoAnnuale, pageable);
    }

    // filtro data di inserimento
    public Page<Cliente> filterDataInserimento(LocalDate dataInserimento, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return clienteRepository.findByDataInserimento(dataInserimento, pageable);
    }

    // filtro data ultimo contatto
    public Page<Cliente> filterUltimoContatto(LocalDate ultimoContatto, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return clienteRepository.findByDataUltimoContatto(ultimoContatto, pageable);
    }

    public Page<Cliente> filterRagioneSociale(String parteRagioneSociale, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        String nomeCapitalized = parteRagioneSociale.substring(0, 1).toUpperCase() + parteRagioneSociale.substring(1).toLowerCase();
        return clienteRepository.findByRagioneSocialeContaining(nomeCapitalized, pageable);
    }

    public Page<Cliente> filterProvincia(String provinciaSedeLegale, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);

        List<Cliente> clientiTrovati = clienteRepository.findAll().stream().filter(
                        c -> c.getSedeLegale().getComune().getProvincia().getNome().equals(provinciaSedeLegale))
                .collect(Collectors.toList());

        return new PageImpl<>(clientiTrovati, pageable, clientiTrovati.size());
    }
    // modifica cliente a inserimento fattura
    public Cliente findByIdAndUpdateFattura(UUID id, double importo, Fattura nuovaFattura) throws ChangeSetPersister.NotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Aggiorna l'ultimo contatto e il fatturato annuale
        cliente.setDataUltimoContatto(LocalDate.now());
        cliente.setFatturatoAnnuale(cliente.getFatturatoAnnuale() + importo);

        // Aggiungi la nuova fattura all'array di fatture del cliente
        cliente.getFatture().add(nuovaFattura);

        // Salva il cliente aggiornato nel repository
        return clienteRepository.save(cliente);
    }

    // modifica cliente
    public Cliente findByIdAndUpdate(UUID id, ClientePayload body) throws ChangeSetPersister.NotFoundException {

        Cliente found = this.findById(id);

        found.setRagioneSociale(body.ragioneSociale());
        found.setPartitaIva(body.partitaIva());
        found.setEmail(body.email());
        found.setPec(body.pec());
        found.setTelefono(body.telefonoCliente());
        found.setNomeContatto(body.nomeContatto());
        found.setCognomeContatto(body.cognomeContatto());
        found.setEmailContatto(body.emailContatto());
        found.setTelefonoContatto(body.telefonoContatto());

        // indirizzo 1
        Comune comuneUno = comuneService.findByNome(body.comuneUno());
        SedeLegale sedeLegale = new SedeLegale(body.civicoUno(), body.viaUno(), body.capUno(), comuneUno, found);

        // indirizzo 2
        Comune comuneDue = comuneService.findByNome(body.comuneDue());
        SedeOperativa sedeOperativa = new SedeOperativa(body.civicoDue(), body.viaDue(), body.capDue(), comuneDue, found);

        return clienteRepository.save(found);
    }

    public Cliente creaCliente(ClientePayload body) throws NotFoundException {

        // Crea un cliente vuoto
        Cliente newCliente = new Cliente();

        //Salva il cliente
//        clienteRepository.save(newCliente);

        // Crea indirizzo 1
        Comune comuneUno = comuneService.findByNome(body.comuneUno());
        SedeLegale sedeLegale = new SedeLegale(body.civicoUno(), body.viaUno(), body.capUno(), comuneUno, newCliente);

        // Crea indirizzo 2
        Comune comuneDue = comuneService.findByNome(body.comuneDue());
        SedeOperativa sedeOperativa = new SedeOperativa(body.civicoDue(), body.viaDue(), body.capDue(), comuneDue, newCliente);

        // Imposta i dettagli del cliente
        newCliente.setRagioneSociale(body.ragioneSociale());
        newCliente.setPartitaIva(body.partitaIva());
        newCliente.setEmail(body.email());
        newCliente.setPec(body.pec());
        newCliente.setTelefono(body.telefonoCliente());
        newCliente.setNomeContatto(body.nomeContatto());
        newCliente.setCognomeContatto(body.cognomeContatto());
        newCliente.setEmailContatto(body.emailContatto());
        newCliente.setTelefonoContatto(body.telefonoContatto());
        newCliente.setDataInserimento(LocalDate.now());
        newCliente.setSedeLegale(sedeLegale);
        newCliente.setSedeOperativa(sedeOperativa);

        // Salva i nuovi indirizzi
        indirizzoDAO.save(sedeLegale);
        indirizzoDAO.save(sedeOperativa);

        // Salva il cliente con gli indirizzi associati
        return clienteRepository.save(newCliente);
    }



}
