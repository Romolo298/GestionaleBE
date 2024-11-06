package org.example.gestionale_be.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "prodotti_storico")
@Data
public class ProdottoStorico {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " sequence_gestionale")
    private Long id;

    private String nome;

    private String tipologia;

    @Column(name = "codice_prodotto")
    private String codiceProdotto;

    private Float prezzo;

    @Column(name = "prezzo_listino")
    private Float prezzoListino;

    private Long quantitativo;

    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;

    @OneToOne
    @JoinColumn(name = "id_fornitore", referencedColumnName = "id")
    private Fornitore fornitore;


}
