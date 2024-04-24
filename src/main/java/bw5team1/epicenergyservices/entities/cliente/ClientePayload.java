package bw5team1.epicenergyservices.entities.cliente;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

public record ClientePayload(

        UUID idCliente,
        String ragioneSociale,
        long partitaIva,
        String email,
        String pec,
        int telefonoCliente,
        String nomeContatto,
        String cognomeContatto,
        String emailContatto,
        int telefonoContatto,
        String viaUno,
        String civicoUno,
        String localitaUno,
        String capUno,
        String comuneUno,
        String viaDue,
        String civicoDue,
        String localitaDue,
        String capDue,
        String comuneDue
) {
}

