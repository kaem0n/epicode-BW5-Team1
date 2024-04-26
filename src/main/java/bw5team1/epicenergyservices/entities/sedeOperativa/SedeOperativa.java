package bw5team1.epicenergyservices.entities.sedeOperativa;

import bw5team1.epicenergyservices.entities.cliente.Cliente;
import bw5team1.epicenergyservices.entities.comune.Comune;
import bw5team1.epicenergyservices.entities.indirizzo.Indirizzo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SedeOperativa extends Indirizzo {
    @OneToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    public SedeOperativa(int civico, String via, int cap, Comune comune, Cliente cliente) {
        super(civico, via, cap, comune);
        this.cliente = cliente;
    }
}
