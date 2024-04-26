package bw5team1.epicenergyservices.entities.comune;

import bw5team1.epicenergyservices.entities.provincia.Provincia;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewComuneDTO(@NotEmpty(message = "Il nome del comune è obbligatorio")
                           @Size(min = 3, max = 20, message = "Il nome del comune deve essere compreso tra 3 e 20 caratteri")
                           String nome,

                           @NotEmpty(message = "La provincia è obbligatoria")
                           @Size(min = 3, max = 20, message = "La provincia deve essere compresa tra 3 e 20 caratteri")
                           Provincia provincia) {
}
