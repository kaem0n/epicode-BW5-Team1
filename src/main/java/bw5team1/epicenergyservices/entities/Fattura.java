package bw5team1.epicenergyservices.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fatture")
public class Fattura {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fattura_id")
    private long id;
    private LocalDate data;
    private int importo;
    private long numero;
    private String stato;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Fattura(LocalDate data, int importo, long numero, String stato, Cliente cliente) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.stato = stato;
        this.cliente = cliente;
    }
}
