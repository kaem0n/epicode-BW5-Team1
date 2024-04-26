package bw5team1.epicenergyservices.entities.comune;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comuni")
public class ComuneController {

    @Autowired
    private ComuneService cs;

    @GetMapping
    public Page<Comune> getComuni(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sort) {
        return cs.getComuni(page, size, sort);
    }

    @GetMapping("/nome/{nome}")
    public Comune findByNome(@PathVariable String nome) {
        return cs.findByNome(nome);
    }

    @GetMapping("/{id}")
    public Comune findById(@PathVariable long id) {
        return cs.findById(id);
    }
}
