package bw5team1.epicenergyservices.entities.cliente;

import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Cliente salvaCliente(@RequestBody ClientePayload body) throws NotFoundException {
//
//        Cliente clienteCreato = clienteService.creaCliente(body);
//
//        return clienteCreato;
//    }

    //--------------------------------------------------------------------------- trova per id
    @GetMapping("/{id}")
    public Cliente findById(@PathVariable UUID id) throws ChangeSetPersister.NotFoundException {
        return clienteService.findById(id);
    }

    //--------------------------------------------------------------------------- elimina
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable UUID id) throws ChangeSetPersister.NotFoundException {
        clienteService.findByIdAndDelete(id);
    }

    @PutMapping("/{Id}")
    public Cliente updateCliente(@PathVariable UUID id, @RequestBody ClientePayload body) throws ChangeSetPersister.NotFoundException {
        return clienteService.findByIdAndUpdate(id, body);
    }

    //--------------------------------------------------------------------------- ordinamenti
    @GetMapping("")
    public Page<Cliente> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "ragioneSociale") String order
    ) {
        return clienteService.findAll(page, order);
    }

    //--------------------------------------------------------------------------- filtro fattura annuale
    @GetMapping("/filter/fatturatoAnnuale")
    public Page<Cliente> filterFattuartoAnnuale(
            @RequestParam double fatturatoAnnuale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return clienteService.filterFatturatoAnnuale(fatturatoAnnuale, page, pageSize);
    }

    //--------------------------------------------------------------------------- filtro data inserimento
    @GetMapping("/filter/dataInserimento")
    public Page<Cliente> filterDataInserimento(
            @RequestParam LocalDate dataInserimento,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return clienteService.filterDataInserimento(dataInserimento, page, pageSize);
    }

    //--------------------------------------------------------------------------- filtro ultimo inserimento
    @GetMapping("/filter/ultimoContatto")
    public Page<Cliente> filterUltimoInserimento(
            @RequestParam LocalDate ultimoContatto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return clienteService.filterUltimoContatto(ultimoContatto, page, pageSize);
    }

    //--------------------------------------------------------------------------- filtro parte ragione sociale
    @GetMapping("/filter/ragioneSociale")
    public Page<Cliente> filterRagioneSociale(
            @RequestParam String parteRagioneSociale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return clienteService.filterRagioneSociale(parteRagioneSociale, page, pageSize);
    }

    //--------------------------------------------------------------------------- filtro provincia sede legale
    @GetMapping("/filter/provinciaSedeLegale")
    public Page<Cliente> filterProvincia(
            @RequestParam String provinciaSedeLegale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return clienteService.filterProvincia(provinciaSedeLegale, page, pageSize);
    }

}

