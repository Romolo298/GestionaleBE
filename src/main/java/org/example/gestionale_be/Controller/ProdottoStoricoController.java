package org.example.gestionale_be.Controller;

import lombok.Data;
import org.example.gestionale_be.Dto.ProdottoStoricoDto;
import org.example.gestionale_be.Service.ProdottoStoricoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/api")
public class ProdottoStoricoController
{
    private final ProdottoStoricoService prodottoStoricoService;

    @GetMapping("/prodotto-storico/{codiceProdotto}")
    public List<ProdottoStoricoDto> recuperaProdotto(@PathVariable("codiceProdotto") String codiceProdotto) {
        return prodottoStoricoService.getStoricoProdottoDaCodiceProdotto(codiceProdotto);
    }

    @GetMapping("/prodotto-lista")
    public List<String> recuperaCodiceProdotto() {
        return prodottoStoricoService.getCodiceProdotto();
    }


}
