package bw5team1.epicenergyservices.entities.indirizzo;


import bw5team1.epicenergyservices.entities.cliente.Cliente;
import bw5team1.epicenergyservices.entities.comune.Comune;
import bw5team1.epicenergyservices.entities.sedeLegale.SedeLegale;
import bw5team1.epicenergyservices.entities.sedeOperativa.SedeOperativa;
import bw5team1.epicenergyservices.exceptions.BadRequestException;
import bw5team1.epicenergyservices.exceptions.NotFoundException;
import bw5team1.epicenergyservices.entities.comune.ComuneDAO;
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

    public Indirizzo saveIndirizzoLegale(NewIndirizzoDTO body, Cliente cliente) {
        indirizzoDAO.findByVia(body.via()).ifPresent(
                indirizzo -> {
                    throw new BadRequestException("This address " + indirizzo.getVia() + " is already registered");
                }
        );

        Optional<Comune> comuneOptional = comuneDAO.findByNome(String.valueOf(body.comune_id()));
        if (!comuneOptional.isPresent()) {
            throw new NotFoundException("Comune with id " + body.comune_id() + " not found");
        }

        Comune comune = comuneOptional.get();
        SedeLegale sedeLegale = new SedeLegale(body.civico(), body.via(), body.cap(), comune, cliente);

        return indirizzoDAO.save(sedeLegale);
    }

    public Indirizzo saveIndirizzoOperativa(NewIndirizzoDTO body, Cliente cliente) {
        indirizzoDAO.findByVia(body.via()).ifPresent(
                indirizzo -> {
                    throw new BadRequestException("This address " + indirizzo.getVia() + " is already registered");
                }
        );

        Optional<Comune> comuneOptional = comuneDAO.findByNome(String.valueOf(body.comune_id()));
        if (!comuneOptional.isPresent()) {
            throw new NotFoundException("Comune with id " + body.comune_id() + " not found");
        }

        Comune comune = comuneOptional.get();
        SedeOperativa sedeOperativa = new SedeOperativa(body.civico(), body.via(), body.cap(), comune, cliente);

        return indirizzoDAO.save(sedeOperativa);
    }


    public Indirizzo findById(UUID id) throws NotFoundException {
        Optional<Indirizzo> indirizzo = indirizzoDAO.findById(String.valueOf(id));
        if (indirizzo.isEmpty()) {
            throw new NotFoundException(id);
        }
        return indirizzo.get();
    }

}
