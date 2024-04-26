package bw5team1.epicenergyservices.entities.provincia;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewProvinciaDTO(@NotEmpty(message = "Il nome della provincia è obbligatorio")
                              @Size(min = 2, max = 4, message = "La sigla della provincia deve essere compreso tra 2 e 4 caratteri")
                              String sigla,
                              @NotEmpty(message = "Il nome della provincia è obbligatorio")
                              @Size(min = 3, max = 20, message = "Il nome della provincia deve essere compreso tra 3 e 20 caratteri")
                              String nome,
                              @NotEmpty(message = "Il nome della provincia è obbligatorio")
                              @Size(min = 2, max = 4, message = "La sigla della provincia deve essere compreso tra 2 e 4 caratteri")
                              String regione
                              ) {
}
