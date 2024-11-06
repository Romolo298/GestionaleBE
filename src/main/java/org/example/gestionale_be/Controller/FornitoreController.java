package org.example.gestionale_be.Controller;


import com.google.i18n.phonenumbers.NumberParseException;
import lombok.Data;
import org.apache.coyote.BadRequestException;
import org.example.gestionale_be.Dto.FornitoreDto;
import org.example.gestionale_be.Service.FornitoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@Data
public class FornitoreController {

    private final FornitoreService fornitoreService;

    @GetMapping("/fornitore/{id}")
    public FornitoreDto recuperaProdotto(@PathVariable("id") Long id) {
        return fornitoreService.getFornitore(id);
    }

    @GetMapping("/fornitore")
    public List<FornitoreDto> recuperaFornitori() {
        return fornitoreService.getFornitori();
    }


    @PostMapping("/fornitore")
    public FornitoreDto modificaInserisciFornitore(@RequestBody FornitoreDto fornitoreDto) throws BadRequestException, NumberParseException {
        return fornitoreService.inserisciModificaFornitore(fornitoreDto);
    }

    @DeleteMapping("/elimina-fornitore/{id}")
    public String eliminaFornitore(@PathVariable("id") Long id) {
        return fornitoreService.eliminaFornitore(id);
    }


}
