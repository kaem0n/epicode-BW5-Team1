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
    @Column(name = "provincia_id")
    private String sigla;
    private String nome;
    private String regione;

    public Provincia(String sigla, String nome, String regione) {
        this.sigla = sigla;
        this.nome = nome;
        this.regione = regione;
    }
}

