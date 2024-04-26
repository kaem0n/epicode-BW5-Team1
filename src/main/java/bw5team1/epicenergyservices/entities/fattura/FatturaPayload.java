package bw5team1.epicenergyservices.entities.fattura;

public record FatturaPayload(String data,
                             double importo,
                             String stato,
                             String idCliente) {
}
