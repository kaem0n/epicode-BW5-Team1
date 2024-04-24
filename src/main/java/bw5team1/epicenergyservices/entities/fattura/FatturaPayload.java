package bw5team1.epicenergyservices.entities.fattura;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

public record FatturaPayload(UUID id, int anno, LocalDate data, double importo, UUID idCliente, String stato) {
}
