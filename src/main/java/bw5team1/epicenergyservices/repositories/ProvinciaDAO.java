package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaDAO extends JpaRepository<Provincia, Long> {
    Optional<Provincia> findByNome(String nome);
}
