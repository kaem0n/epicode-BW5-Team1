package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtenteDAO extends JpaRepository<Utente, UUID> {

    boolean existsByEmail(String email);
    Optional<Utente> findByEmail(String email);
}
