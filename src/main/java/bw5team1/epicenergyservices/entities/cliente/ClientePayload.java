package bw5team1.epicenergyservices.entities.cliente;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

public record ClientePayload(

//        UUID idCliente,
        String ragioneSociale,
        long partitaIva,
        String email,
        String pec,
        String telefonoCliente, //telefono
        String nomeContatto,
        String cognomeContatto,
        String emailContatto,
        String telefonoContatto,
        String viaUno,
        int civicoUno,
//        String localitaUno,
        int capUno,
        String comuneUno,
        String viaDue,
        int civicoDue,
//        String localitaDue,
        int capDue,
        String comuneDue
) {
}

