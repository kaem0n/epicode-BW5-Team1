package bw5team1.epicenergyservices.controllers;

import bw5team1.epicenergyservices.entities.Utente;
import bw5team1.epicenergyservices.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    // GET http://localhost:3001/utenti
    @GetMapping
    public Page<Utente> getUtenti(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sort) {
        return utenteService.getUtente(page, size, sort);
    }

    // GET http://localhost:3001/utenti/{id}

    @GetMapping("/{utentiId}")
    public Utente findById(@PathVariable UUID utentiId) {

        return utenteService.findById(utentiId);
    }

    // PUT http://localhost:3001/utenti/{id} (+ req.body)

    @PutMapping("/{utentiId}")
    public Utente findAndUpdate(@PathVariable UUID utentiId, @RequestBody Utente body){
        return utenteService.findByIdAndUpdate(utentiId, body);
    }

    // DELETE http://localhost:3001/utenti/{id}

    @DeleteMapping("/{utenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> findByIdAndDelete(@PathVariable UUID utenteId) {

        utenteService.findByIDAndDelete(utenteId);
        return ResponseEntity.ok("L'utente è stato cancellato con successo");
    }

    // POST http://localhost:3001/utenti/{id}/avatar (+ file)

    @PostMapping("/{utentiId}/avatar")
    public Utente updateAvatar(MultipartFile file, UUID utentiId){
        try{
            return utenteService.uploadAvatar(utentiId, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
