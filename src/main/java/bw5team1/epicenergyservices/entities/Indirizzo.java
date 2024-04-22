package bw5team1.epicenergyservices.entities;

import bw5team1.epicenergyservices.entities.comune.Comune;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "indirizzi")
public abstract class Indirizzo {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indirizzo_id")
    private UUID id;
    private String via;
    private int civico;
    private int cap;
    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;

    public Indirizzo(int civico, String via, int cap, Comune comune) {
        this.civico = civico;
        this.via = via;
        this.cap = cap;
        this.comune = comune;
    }
}
