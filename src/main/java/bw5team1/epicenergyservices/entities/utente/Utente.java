package bw5team1.epicenergyservices.entities.utente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "utenti")
@JsonIgnoreProperties({"password", "authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked", "enabled"})
public class Utente implements UserDetails {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "utente_id")
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String nome;
    private String cognome;
    @Column(name = "avatar")
    private String avatarUrl;
    @NotNull
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.tipo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
