package bw5team1.epicenergyservices.entities.fattura;

import java.time.LocalDate;
import java.util.UUID;

public record FatturaPayload(String data,
                             double importo,
                             String stato,
                             String idCliente) {
}
