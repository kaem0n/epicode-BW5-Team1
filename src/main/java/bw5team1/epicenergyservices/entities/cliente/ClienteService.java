package bw5team1.epicenergyservices.entities.cliente;

import bw5team1.epicenergyservices.entities.fattura.Fattura;
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
}
