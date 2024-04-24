package bw5team1.epicenergyservices.payloads.indirizzo;

import bw5team1.epicenergyservices.entities.comune.Comune;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewIndirizzoDTO(@NotEmpty(message = "La via è obbligatoria")
                              String via,
                              @NotNull(message = "Il civico è obbligatorio")
                              int civico,
                              @NotNull(message = "Il cap è obbligatorio")
                              int cap,
                              @NotNull(message = "Il comune è obbligatorio")
                              UUID comune_id) {

}
