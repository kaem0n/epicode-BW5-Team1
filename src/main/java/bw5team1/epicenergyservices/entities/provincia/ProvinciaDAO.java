package bw5team1.epicenergyservices.entities.provincia;



import bw5team1.epicenergyservices.entities.provincia.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaDAO extends JpaRepository<Provincia, String> {
    Optional<Provincia> findBySigla(String sigla);
    Optional<Provincia> findByNome(String nome);
}
