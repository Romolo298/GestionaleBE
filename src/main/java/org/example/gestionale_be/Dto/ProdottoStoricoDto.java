package org.example.gestionale_be.Dto;


import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class ProdottoStoricoDto {

     private Long id;

    private String nome;

    private String tipologia;

    private String codiceProdotto;

    private Float prezzo;

    private Float prezzoListino;

    private Long quantitativo;

    private LocalDateTime dataCreazione;

    private FornitoreDto fornitore;


}
