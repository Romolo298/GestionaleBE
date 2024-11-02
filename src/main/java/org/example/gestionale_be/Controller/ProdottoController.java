package org.example.gestionale_be.Controller;


import lombok.Data;
import org.apache.coyote.BadRequestException;
import org.example.gestionale_be.Dto.ProdottoDto;

import org.example.gestionale_be.Service.ProdottoService;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@Data
public class ProdottoController {

    private final ProdottoService prodottoService;
    @GetMapping("/prodotto/{id}")
    public ProdottoDto recuperaProdotto(@PathVariable("id") Long id) {
        return prodottoService.getProdotto(id);
    }

    @GetMapping("/prodotto")
    public List<ProdottoDto> recuperProdotti() {
        return prodottoService.getProdotti();
    }

    @PostMapping("/inserisci-modifica/prodotto")
    public ProdottoDto inserisciProdotto(@RequestBody ProdottoDto prodottoDto) throws BadRequestException {
        if(prodottoDto.getId()!=null)
            throw new BadRequestException("Non puoi inserire questo prodotto, id PRESENTE");
        return prodottoService.inserisciProdotto(prodottoDto);
    }

    @PutMapping()
    public ProdottoDto modificaProdotto(@RequestBody ProdottoDto prodottoDto) throws BadRequestException {
        if(prodottoDto.getId()==null)
            throw new BadRequestException("Non puoi inserire questo prodotto, id non PRESENTE");
        return prodottoService.modificaProdotto(prodottoDto);
    }

    @DeleteMapping("/elimina-prodotto/{id}")
    public String eliminaProdotto(@PathVariable("id") Long id){
        return prodottoService.eliminaProdotto(id);
    }


}
