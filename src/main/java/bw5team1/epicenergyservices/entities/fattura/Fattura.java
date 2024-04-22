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
    private UUID id;
    private LocalDate data;
    //CAMBIATO IMPORTO DA INT A DOUBLE
    private double importo;
    //CAMBIATO IMPORTO DA LONG A DOUBLE
    private double numero;
    private String stato;
    //CAMBIATO DA CLIENTE A UUID
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private UUID idCliente;

    public Fattura(LocalDate data, double importo, double numero, String stato, UUID idCliente) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.stato = stato;
        this.idCliente = idCliente;
    }

    //    public Fattura(LocalDate data, int importo, long numero, String stato, Cliente cliente) {
//        this.data = data;
//        this.importo = importo;
//        this.numero = numero;
//        this.stato = stato;
//        this.cliente = cliente;
//    }
}
