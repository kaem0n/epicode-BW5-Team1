package bw5team1.epicenergyservices.entities.indirizzo;

import bw5team1.epicenergyservices.entities.indirizzo.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndirizzoDAO extends JpaRepository<Indirizzo, String> {
    Optional<Indirizzo> findByVia(String via);
}
