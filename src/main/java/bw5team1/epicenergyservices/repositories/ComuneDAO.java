package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComuneDAO extends JpaRepository<Comune, String> {
    Optional<Comune> findByNome(String nome);
}
