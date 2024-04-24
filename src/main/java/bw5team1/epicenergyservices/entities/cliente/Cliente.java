package bw5team1.epicenergyservices.entities.cliente;

import bw5team1.epicenergyservices.entities.fattura.Fattura;
import bw5team1.epicenergyservices.entities.sedeLegale.SedeLegale;
import bw5team1.epicenergyservices.entities.sedeOperativa.SedeOperativa;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
//AGGIUNTO @AllArgsConstructor
@AllArgsConstructor
//AGGIUNTO @Builder
@Builder
@Entity
@Table(name = "clienti")
public class Cliente {
    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "cliente_id")
    //CAMBIATO DA LONG A UUID
    private UUID id;
    @Column(name = "ragione_sociale")
    private String ragioneSociale;
    @Column(name = "partita_iva")
    private long partitaIva;
    private String email;
    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;
    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;
    // CAMBIATO DA INT A DOUBLE
    @Column(name = "fatturato_annuale")
    private double fatturatoAnnuale;
    private String pec;
    private String telefono;
    @Column(name = "email_contatto")
    private String emailContatto;
    @Column(name = "nome_contatto")
    private String nomeContatto;
    @Column(name = "cognome_contatto")
    private String cognomeContatto;
    @Column(name = "telefono_contatto")
    private String telefonoContatto;
    @Column(name = "logo")
    private String logoUrl;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;
    @OneToOne
    @JoinColumn(name = "sede_legale_id")
    private SedeLegale sedeLegale;
    @OneToOne
    @JoinColumn(name = "sede_operativa_id")
    private SedeOperativa sedeOperativa;
    @OneToMany
    @Builder.Default
    private List<Fattura> fatture = new ArrayList<Fattura>();

    public Cliente(String ragioneSociale, long partitaIva, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto, int fatturatoAnnuale, String pec, String telefono, String nomeContatto, String emailContatto, String cognomeContatto, String telefonoContatto, String logoUrl, TipoCliente tipo, SedeLegale sedeLegale, SedeOperativa sedeOperativa) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.nomeContatto = nomeContatto;
        this.emailContatto = emailContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoUrl = logoUrl;
        this.tipo = tipo;
        this.sedeLegale = sedeLegale;
        this.sedeOperativa = sedeOperativa;
    }
}
