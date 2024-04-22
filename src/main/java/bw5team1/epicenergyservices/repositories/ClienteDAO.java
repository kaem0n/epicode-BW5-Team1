package bw5team1.epicenergyservices.repositories;

import bw5team1.epicenergyservices.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByPartitaIva(long partitaIva);
}
