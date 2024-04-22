package bw5team1.epicenergyservices.entities.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepo;

    public Provincia create(String sigla, String provincia, String regione) {

        Provincia p = new Provincia(sigla, provincia);

        return provinciaRepo.save(p);
    }

    public List<Provincia> find() {
        return provinciaRepo.findAll();
    }

    public Page<Provincia> findAll(int page, String ordinamento) {
        Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
        return provinciaRepo.findAll(pagina);
    }

    public Provincia findByName(String nomeProvincia) {
        return provinciaRepo.findByProvinciaIgnoreCase(nomeProvincia);
    }

}
