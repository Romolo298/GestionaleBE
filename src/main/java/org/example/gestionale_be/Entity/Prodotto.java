package org.example.gestionale_be.Entity;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "prodotti")
@Data
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_gestionale")
    private Long id;

    private String nome;

    private String tipologia;

    @NotNull
    @Column(name = "codice_prodotto")
    private String codiceProdotto;

    private Float prezzo;

    @Column(name = "prezzo_listino")
    private Float prezzoListino;

    private Long quantitativo;

    private String urlImmagine;

    @Column(name = "data_creazione")
    private LocalDate dataCreazione;

    @Column(name = "data_modifica")
    private LocalDate dataModifica;

    @OneToOne
    @JoinColumn(name = "id_fornitore", referencedColumnName = "id")
    private Fornitore fornitore;

}
