package bw5team1.epicenergyservices.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sedi_legali")
public class SedeLegale extends Indirizzo {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sede_legale_id")
    private long id;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public SedeLegale(int civico, String via, int cap, Comune comune, Cliente cliente) {
        super(civico, via, cap, comune);
        this.cliente = cliente;
    }
}
