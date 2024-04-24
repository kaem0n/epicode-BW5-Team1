package bw5team1.epicenergyservices.entities.comune;

import bw5team1.epicenergyservices.entities.provincia.Provincia;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comuni")
public class Comune {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comune_id")
    private UUID id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;


}
