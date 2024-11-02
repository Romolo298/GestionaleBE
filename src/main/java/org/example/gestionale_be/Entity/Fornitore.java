package org.example.gestionale_be.Entity;


import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "fornitori")
@Data
public class Fornitore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " sequence_gestionale")
    private Long id;

    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    private String email;

    private String pec;

    @Column(name = "numero_telefono")
    private String numeroTelefono;


}
