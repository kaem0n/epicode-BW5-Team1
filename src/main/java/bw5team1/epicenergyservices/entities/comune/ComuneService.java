package bw5team1.epicenergyservices.entities.comune;

import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ComuneService {
    @Autowired
    private ComuneDAO cd;

    public Comune findByNome(String nome) {
        return cd.findByNomeIgnoreCase(nome).orElseThrow(() -> new NotFoundException(nome));
    }

    public Page<Comune> getComuni(int page, int size, String sort) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return cd.findAll(pageable);
    }

    public Comune findById(long id) {
        return cd.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }
}
