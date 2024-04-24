package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndirizzoDAO extends JpaRepository<Indirizzo, String> {
    Optional<Indirizzo> findByVia(String via);
}
