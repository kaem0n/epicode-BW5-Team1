package bw5team1.epicenergyservices.entities.provincia;

import bw5team1.epicenergyservices.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaDAO pd;

    public Provincia findByName(String nome){
        return pd.findByNome(nome).orElseThrow(() -> new NotFoundException(nome));
    }

    public Provincia save(Provincia p) {
        return pd.save(p);
    }
}
