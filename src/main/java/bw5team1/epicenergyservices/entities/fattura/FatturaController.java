package bw5team1.epicenergyservices.entities.fattura;

import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/fattura")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping("")
    public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "id") String order) {
        return fatturaService.findAll(page, order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura salvaFattura(@RequestBody FatturaPayload body) throws Exception {

        Fattura fatturaCreata = fatturaService.creaFattura(body);

        return fatturaCreata;

    }

    // ---------------------------------------------------------------------------
    // trova per id
    @GetMapping("/{id}")
    public Fattura findById(@PathVariable UUID id) throws NotFoundException {
        return fatturaService.findById(id);
    }

    // ---------------------------------------------------------------------------
    // elimina
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable UUID id) throws NotFoundException {
        fatturaService.findByIdAndDelete(id);
    }

    // ---------------------------------------------------------------------------
    // filtro per cliente
    @GetMapping("/filter/ragioneSociale")
    public Page<Fattura> filterByRagioneSociale(@RequestParam String ragioneSociale,
                                                @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return fatturaService.filterByCliente(ragioneSociale, page, pageSize);
    }

    // ---------------------------------------------------------------------------
    // filtro per stato fattura
    @GetMapping("/filter/statoFattura")
    public Page<Fattura> filterByStatoFattura(@RequestParam String statoFattura,
                                              @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return fatturaService.filterByStatoFattura(statoFattura, page, pageSize);
    }

    // ---------------------------------------------------------------------------
    // filtro per data
    @GetMapping("/filter/data")
    public Page<Fattura> filterByData(@RequestParam LocalDate data, @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int pageSize) {
        return fatturaService.filterByData(data, page, pageSize);
    }

    //---------------------------------------------------------------------------
    // filtro per anno
    @GetMapping("/filter/anno")
    public Page<Fattura> filterByAnno(
            @RequestParam int anno,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return fatturaService.filterByAnno(anno, page, pageSize);
    }


    // ---------------------------------------------------------------------------
    // filtro per range importi
    @GetMapping("/filter/importRange")
    public Page<Fattura> filterByImportRange(@RequestParam double minImporto, @RequestParam double maxImporto,
                                             @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return fatturaService.filterByImportRange(minImporto, maxImporto, page, pageSize);
    }
}
