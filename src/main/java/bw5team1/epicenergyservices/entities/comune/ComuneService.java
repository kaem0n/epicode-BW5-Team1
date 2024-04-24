package bw5team1.epicenergyservices.entities.comune;

import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComuneService {
    @Autowired
    private ComuneDAO cd;

    public Comune findByNome(String nome) {
        return cd.findByNome(nome).orElseThrow(() -> new NotFoundException(nome));
    }
}
