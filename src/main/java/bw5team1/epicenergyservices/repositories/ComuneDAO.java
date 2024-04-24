package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.comune.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComuneDAO extends JpaRepository<Comune, Long> {
    Optional<Comune> findByNome(String nome);

}
