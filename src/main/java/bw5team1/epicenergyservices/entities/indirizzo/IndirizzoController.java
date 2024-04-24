package bw5team1.epicenergyservices.entities.indirizzo;

import bw5team1.epicenergyservices.entities.indirizzo.Indirizzo;
import bw5team1.epicenergyservices.entities.indirizzo.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @RequestMapping
    public Page<Indirizzo> getIndirizzo(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String sort) {
        return indirizzoService.getIndirizzo(page, size, sort);
    }


    @RequestMapping("/{indirizziId}")
    public Indirizzo findById(@PathVariable UUID indirizziId) {
        return indirizzoService.findById(indirizziId);
    }
}
