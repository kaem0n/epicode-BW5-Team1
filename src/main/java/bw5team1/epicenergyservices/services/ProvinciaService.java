package bw5team1.epicenergyservices.services;

import bw5team1.epicenergyservices.repositories.ProvinciaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaDAO pd;
}
