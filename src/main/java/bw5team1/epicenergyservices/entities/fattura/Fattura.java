package bw5team1.epicenergyservices.entities.fattura;

import bw5team1.epicenergyservices.entities.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "fatture")
public class Fattura {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fattura_id")
    private long id;
    private LocalDate data;
    //CAMBIATO IMPORTO DA INT A DOUBLE
    private double importo;
    //CAMBIATO IMPORTO DA LONG A DOUBLE
    private String stato;
    //CAMBIATO DA CLIENTE A UUID
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Fattura(LocalDate data, double importo, String stato, Cliente cliente) {
        this.data = data;
        this.importo = importo;
        this.stato = stato;
        this.cliente = cliente;
    }
}
