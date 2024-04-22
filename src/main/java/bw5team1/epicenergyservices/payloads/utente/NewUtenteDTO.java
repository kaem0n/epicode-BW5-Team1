package bw5team1.epicenergyservices.payloads.utente;

import bw5team1.epicenergyservices.enums.TipoUtente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(@NotEmpty(message = "L'username è obbligatorio")
                           @Size(min = 4, max = 20, message = "L'username deve essere composto da almeno 4 caratteri e massimo 20")
                           String username,
                           @NotEmpty(message = "La password è obbligatorio")
                           @Size(min = 4, max = 20, message = "La password deve essere composto da almeno 4 caratteri e massimo 20")
                           String password,
                           @NotEmpty(message = "L'email è obbligatoria")
                           @Email(message = "L'email inserita non è valida")
                           String email,
                           @NotEmpty(message = "Il nome è obbligatorio")
                           @Size(min = 4, max = 20, message = "Il nome deve essere composto da almeno 4 caratteri e massimo 20")
                           String nome,
                           @NotEmpty(message = "Il cognome è obbligatorio")
                           @Size(min = 4, max = 20, message = "Il cognome deve essere composto da almeno 4 caratteri e massimo 20")
                           String cognome,
                           @NotEmpty(message = "Il tipo utente è obbligatorio")
                           @Pattern(regexp = "USER|ADMIN", message = "Il tipo utente deve essere USER o ADMIN")
                           TipoUtente tipo) {
}
