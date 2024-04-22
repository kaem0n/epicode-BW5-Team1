package bw5team1.epicenergyservices.entities.provincia;

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
@Table(name = "province")
public class Provincia {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provincia_id")
    private long id;
    private String nome;
    private String sigla;

    public Provincia(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }
}
