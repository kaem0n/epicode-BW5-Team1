package bw5team1.epicenergyservices.entities.fattura;

import bw5team1.epicenergyservices.exceptions.BadRequestException;
import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/fatture")
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
    public Fattura salvaFattura(@RequestBody FatturaPayload body){
        return fatturaService.creaFattura(body);
    }

    // ---------------------------------------------------------------------------
    // trova per id
    @GetMapping("/{id}")
    public Fattura findById(@PathVariable long id) throws NotFoundException {
        return fatturaService.findById(id);
    }

    // ---------------------------------------------------------------------------
    // elimina
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable long id) throws NotFoundException {
        fatturaService.findByIdAndDelete(id);
    }

    // ---------------------------------------------------------------------------
    // filtro per cliente
    @GetMapping("/filter")
    public Page<Fattura> filterByPec(@RequestParam(required = false) String pec,
                                     @RequestParam(required = false) String statoFattura,
                                     @RequestParam(required = false) LocalDate data,
                                     @RequestParam(required = false) String minImporto,
                                     @RequestParam(required = false) String maxImporto,
                                     @RequestParam(required = false) String anno,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int pageSize) {
        if (pec != null) return fatturaService.filterByCliente(pec, page, pageSize);
        else if (statoFattura != null) return fatturaService.filterByStatoFattura(statoFattura, page, pageSize);
        else if (data != null) return fatturaService.filterByData(data, page, pageSize);
        else if (minImporto != null && maxImporto != null) {
            return fatturaService.filterByImportRange(Double.parseDouble(minImporto), Double.parseDouble(maxImporto), page, pageSize);
        }
        else if (anno != null) return fatturaService.filterByAnno(Integer.parseInt(anno), page, pageSize);
        else throw new BadRequestException("Request is missing parameters.");
    }

    //---------------------------------------------------------------------------
    // filtro per anno
//    @GetMapping("/filter/anno")
//    public Page<Fattura> filterByAnno(
//            @RequestParam int anno,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int pageSize
//    ) {
//        return fatturaService.filterByAnno(anno, page, pageSize);
//    }

}
