package bw5team1.epicenergyservices.entities.comune;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComuneDAO extends JpaRepository<Comune, Long> {
    Optional<Comune> findByNomeIgnoreCase(String nome);

}
