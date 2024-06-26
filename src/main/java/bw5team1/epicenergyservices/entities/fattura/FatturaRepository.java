package bw5team1.epicenergyservices.entities.fattura;

import bw5team1.epicenergyservices.entities.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

    Page<Fattura> findByCliente(Cliente cliente, Pageable pageable);
    Page<Fattura> findByStato(String stato, Pageable pageable);
    Page<Fattura> findByData(LocalDate data, Pageable pageable);
    Page<Fattura> findByImportoBetween(double minImporto, double maxImporto, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE EXTRACT(YEAR FROM f.data) = :anno")
    Page<Fattura> findByAnno(int anno, Pageable pageable);
}
