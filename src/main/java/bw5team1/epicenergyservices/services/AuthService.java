package bw5team1.epicenergyservices.services;

import bw5team1.epicenergyservices.entities.utente.Utente;
import bw5team1.epicenergyservices.entities.utente.UtenteService;
import bw5team1.epicenergyservices.exceptions.UnauthorizedException;
import bw5team1.epicenergyservices.entities.utente.UtenteLoginDTO;
import bw5team1.epicenergyservices.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUtenteAndGenerateToken(UtenteLoginDTO payload){

        Utente utente = this.utenteService.findByEmail(payload.email());

        if(bcrypt.matches(payload.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }
}
