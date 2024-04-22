package bw5team1.epicenergyservices.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private long id;
    private String nome;
    private String sigla;
}
