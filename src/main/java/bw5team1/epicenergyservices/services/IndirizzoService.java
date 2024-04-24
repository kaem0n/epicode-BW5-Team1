package bw5team1.epicenergyservices.services;

import bw5team1.epicenergyservices.entities.Indirizzo;
import bw5team1.epicenergyservices.entities.comune.Comune;
import bw5team1.epicenergyservices.exceptions.BadRequestException;
import bw5team1.epicenergyservices.exceptions.NotFoundException;
import bw5team1.epicenergyservices.payloads.indirizzo.NewIndirizzoDTO;
import bw5team1.epicenergyservices.repositories.ComuneDAO;
import bw5team1.epicenergyservices.repositories.IndirizzoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoDAO indirizzoDAO;

    @Autowired
    private ComuneDAO comuneDAO;

    public Page<Indirizzo> getIndirizzo(int page, int size, String sort){
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return indirizzoDAO.findAll(pageable);
    }

    public Indirizzo findById(UUID id) throws NotFoundException {
        Optional<Indirizzo> indirizzo = indirizzoDAO.findById(String.valueOf(id));
        if (indirizzo.isEmpty()) {
            throw new NotFoundException(id);
        }
        return indirizzo.get();
    }

}
