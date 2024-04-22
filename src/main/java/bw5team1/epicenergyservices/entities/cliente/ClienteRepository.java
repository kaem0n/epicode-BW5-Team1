package bw5team1.epicenergyservices.entities.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByPec(String pec);

    Optional<Cliente> findByRagioneSociale(String ragioneSociale);

    Page<Cliente> findByFatturatoAnnuale(double fatturatoAnnuale, Pageable pageable);

    Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);

    Page<Cliente> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);

    Page<Cliente> findByRagioneSocialeContaining(String ragioneSociale, Pageable pageable);

}
