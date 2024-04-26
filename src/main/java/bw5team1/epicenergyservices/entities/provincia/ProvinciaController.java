package bw5team1.epicenergyservices.entities.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public Page<Provincia> getProvincia(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "sigla") String sort) {
        return provinciaService.findAll(page, size, sort);
    }

    @GetMapping("/{sigla}")
    public Provincia findById(@PathVariable String sigla) {
        return provinciaService.findById(sigla);
    }

    @GetMapping("/nome/{nome}")
    public Provincia findByName(@PathVariable String nome) {
        return provinciaService.findByName(nome);
    }
}
