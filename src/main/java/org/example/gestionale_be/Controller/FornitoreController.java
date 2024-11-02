package org.example.gestionale_be.Controller;


import com.google.i18n.phonenumbers.NumberParseException;
import lombok.Data;
import org.apache.coyote.BadRequestException;
import org.example.gestionale_be.Dto.FornitoreDto;
import org.example.gestionale_be.Dto.ProdottoDto;
import org.example.gestionale_be.Service.FornitoreService;
import org.example.gestionale_be.Service.ProdottoService;
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
    public FornitoreDto inserisciProdotto(@RequestBody FornitoreDto fornitoreDto) throws BadRequestException, NumberParseException {
        if(fornitoreDto.getId()!=null)
            throw new BadRequestException("Non puoi inserire un fornitore con un id PRESENTE");
        return fornitoreService.inserisciFornitore(fornitoreDto);
    }

    @PutMapping("/fornitore")
    public FornitoreDto modificaFornitore(@RequestBody FornitoreDto fornitoreDto) throws BadRequestException {
        if(fornitoreDto.getId()==null)
            throw new BadRequestException("Non puoi modificare un fornitore senza un id PRESENTE");
        return fornitoreService.modificaFornitore(fornitoreDto);
    }

    @DeleteMapping("/elimina-fornitore/{id}")
    public String eliminaFornitore(@PathVariable("id") Long id){
        return fornitoreService.eliminaFornitore(id);
    }


}
