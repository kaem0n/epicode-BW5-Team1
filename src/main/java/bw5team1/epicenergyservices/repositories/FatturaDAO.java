package bw5team1.epicenergyservices.repositories;



import bw5team1.epicenergyservices.entities.fattura.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FatturaDAO extends JpaRepository<Fattura, Long> {
    Optional<Fattura> findByNumero(long numero);
}
