package bw5team1.epicenergyservices.entities.utente;

import bw5team1.epicenergyservices.entities.utente.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteDAO extends JpaRepository<Utente, UUID> {
    boolean existsByEmail(String email);
    Optional<Utente> findByEmail(String email);
}
