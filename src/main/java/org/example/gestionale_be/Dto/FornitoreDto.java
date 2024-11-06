package org.example.gestionale_be.Dto;


import lombok.Data;


@Data
public class FornitoreDto {
    private Long id;
    private String ragioneSociale;
    private String email;
    private String pec;
    private String numeroTelefono;

}
