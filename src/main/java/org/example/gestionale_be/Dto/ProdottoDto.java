package org.example.gestionale_be.Dto;

import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class ProdottoDto {

    private Long id;
    private String nome;
    private String tipologia;
    @NotBlank
    private String codiceProdotto;
    private Float prezzo;
    private Float prezzoListino;
    private Long quantitativo;
    private String urlImmagine;
    private LocalDate dataCreazione;
    private LocalDate dataModifica;
    private FornitoreDto fornitoreDto;
}
