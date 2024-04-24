package bw5team1.epicenergyservices.entities.provincia;

import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaDAO provinciaDAO;


    public Page<Provincia> findAll(int page, int size, String sort) {
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return provinciaDAO.findAll(pageable);
    }

    public Provincia findById(String sigla) {
        return provinciaDAO.findById(sigla).orElseThrow(() ->new NotFoundException(sigla));
    }

    public Provincia findByName(String nome){
        return provinciaDAO.findByNome(nome).orElseThrow(() -> new NotFoundException(nome));
    }


}
