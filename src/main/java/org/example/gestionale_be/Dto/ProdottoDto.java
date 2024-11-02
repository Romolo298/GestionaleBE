package org.example.gestionale_be.Dto;

import lombok.Data;


@Data
public class ProdottoDto {

    private Long id;
    private String nome;
    private String tipologia;
    private String codiceProdotto;
    private Float prezzo;
    private Float prezzoListino;
    private Long quantitativo;
    private String urlImmagine;
}
