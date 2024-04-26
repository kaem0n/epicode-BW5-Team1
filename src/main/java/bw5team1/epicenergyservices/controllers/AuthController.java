package bw5team1.epicenergyservices.controllers;

import bw5team1.epicenergyservices.exceptions.BadRequestException;
import bw5team1.epicenergyservices.payloads.utente.NewUtenteDTO;
import bw5team1.epicenergyservices.payloads.utente.NewUtenteRespDTO;
import bw5team1.epicenergyservices.payloads.utente.UtenteLoginDTO;
import bw5team1.epicenergyservices.payloads.utente.UtenteLoginRespDTO;
import bw5team1.epicenergyservices.services.AuthService;
import bw5team1.epicenergyservices.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginRespDTO login(@RequestBody UtenteLoginDTO payload){

        return new UtenteLoginRespDTO(this.authService.authenticateUtenteAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO saveUser(@RequestBody @Validated NewUtenteDTO body, BindingResult validation) throws IOException {

        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewUtenteRespDTO(this.utenteService.save(body).getId());
    }
}
