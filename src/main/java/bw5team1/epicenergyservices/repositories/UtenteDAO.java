package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteDAO extends JpaRepository<Utente, Long> {
    Optional<UtenteDAO> findByEmail(String email);
}
