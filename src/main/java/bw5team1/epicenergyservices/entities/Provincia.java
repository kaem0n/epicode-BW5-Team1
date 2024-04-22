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
@Table(name = "province")
public class Provincia {
    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "provincia_id")
    private String sigla;
    private String nome;
    private String regione;

    public Provincia(String nome, String regione, String sigla) {
        this.nome = nome;
        this.regione = regione;
        this.sigla = sigla;
    }
}
