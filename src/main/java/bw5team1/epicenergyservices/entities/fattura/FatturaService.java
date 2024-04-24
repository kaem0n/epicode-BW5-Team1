package bw5team1.epicenergyservices.entities.fattura;

import bw5team1.epicenergyservices.entities.cliente.ClienteRepository;
import bw5team1.epicenergyservices.entities.cliente.ClienteService;
import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FatturaService {
    @Autowired
    FatturaRepository fatturaRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    // creazione cliente
    public Fattura creaFattura(FatturaPayload body) throws Exception {

        Optional<Fattura> fatturaMax = fatturaRepository.findAll().stream()
                .max((f1, f2) -> Double.compare(f1.getNumero(), f2.getNumero()));

        double f = 1;

        if (fatturaMax.isPresent())
            f = fatturaMax.get().getNumero() + 1;

        Fattura newFattura = Fattura.builder()
                .data(body.data())
                .importo(body.importo())
                .numero(f)
                .stato(body.stato())
                .idCliente(body.idCliente())
                .build();

        // Salvataggio della nuova fattura nel repository delle fatture
        newFattura = fatturaRepository.save(newFattura);

        // Aggiornamento dati cliente (ultimo contatto e fatturato annuo)
        clienteService.findByIdAndUpdateFattura(body.idCliente(), body.importo(), newFattura);

        return newFattura;
    }

    public List<Fattura> find() {
        return fatturaRepository.findAll();
    }

    public Page<Fattura> findAll(int page, String sort) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));
        return fatturaRepository.findAll(pageable);
    }


    //IN ATTESA DI EXCEPTION
    public Fattura findById(UUID id) throws NotFoundException {
        return fatturaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Fattura findByIdAndUpdate(UUID id, FatturaPayload body) throws NotFoundException {
        Fattura fatturaTrovata = this.findById(id);
        fatturaTrovata.setStato(body.stato());
        fatturaTrovata.setImporto(body.importo());
        return fatturaRepository.save(fatturaTrovata);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Fattura fatturaTrovata = this.findById(id);
        fatturaRepository.delete(fatturaTrovata);
    }

    // filtro per cliente
    public Page<Fattura> filterByCliente(String ragioneSociale, int page, int pageSize) {
        UUID clienteId = clienteRepository.findByRagioneSociale(ragioneSociale).get().getId();
        Pageable pageable = PageRequest.of(page, pageSize);
        return fatturaRepository.findByIdCliente(clienteId, pageable);
    }

    // filtro per stato fattura
    public Page<Fattura> filterByStatoFattura(String stato, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fatturaRepository.findByStato(stato, pageable);
    }

    // filtro per data
    public Page<Fattura> filterByData(LocalDate data, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fatturaRepository.findByData(data, pageable);
    }

    // filtro per anno
    public Page<Fattura> filterByAnno(int anno, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fatturaRepository.findByAnno(anno, pageable);
    }

    // filtro per importo
    public Page<Fattura> filterByImportRange(double minImporto, double maxImporto, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fatturaRepository.findByImportoBetween(minImporto, maxImporto, pageable);
    }
}
