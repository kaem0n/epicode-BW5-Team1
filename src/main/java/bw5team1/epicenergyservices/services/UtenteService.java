package bw5team1.epicenergyservices.services;

import bw5team1.epicenergyservices.entities.Utente;
import bw5team1.epicenergyservices.payloads.utente.NewUtenteDTO;
import bw5team1.epicenergyservices.repositories.UtenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UtenteService {

    @Autowired
    private UtenteDAO utenteDAO;
}


