package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneDAO extends JpaRepository<Comune, Long> {
}
