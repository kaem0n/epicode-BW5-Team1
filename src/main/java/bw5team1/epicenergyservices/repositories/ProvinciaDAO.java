package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaDAO extends JpaRepository<Provincia, String> {
    Optional<Provincia> findBySigla(String sigla);
}
