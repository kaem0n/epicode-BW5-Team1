package bw5team1.epicenergyservices.entities.provincia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaDAO extends JpaRepository<Provincia, String> {
    Optional<Provincia> findByNomeIgnoreCase(String nome);
}
