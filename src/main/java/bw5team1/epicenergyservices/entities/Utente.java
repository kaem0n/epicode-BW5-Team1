package bw5team1.epicenergyservices.entities;

import bw5team1.epicenergyservices.enums.TipoUtente;
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
@Table(name = "utenti")
public class Utente {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utente_id")
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String nome;
    private String cognome;
    @Column(name = "avatar")
    private String avatarUrl;
    @Enumerated(EnumType.STRING)
    private TipoUtente tipo;

    public Utente(String username, String password, String email, String nome, String cognome, String avatarUrl) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.avatarUrl = avatarUrl;
        this.tipo = TipoUtente.USER;
    }

}
