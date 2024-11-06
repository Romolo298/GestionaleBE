package org.example.gestionale_be.Service;

import com.google.i18n.phonenumbers.NumberParseException;
import lombok.Data;
import org.apache.coyote.BadRequestException;
import org.example.gestionale_be.Dto.ProdottoDto;
import org.example.gestionale_be.Dto.ProdottoStoricoDto;
import org.example.gestionale_be.Entity.Prodotto;
import org.example.gestionale_be.Mapper.ProdottoStoricoMapper;
import org.example.gestionale_be.Repository.ProdottoStoricoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ProdottoStoricoService {
    private final ProdottoStoricoRepository prodottoStoricoRepository;

    private final ProdottoStoricoMapper prodottoStoricoMapper;

    private final FornitoreService fornitoreService;

    public void inserimentoStorico(Prodotto prodotto, ProdottoDto prodottoDto) throws NumberParseException, BadRequestException {
        ProdottoStoricoDto prodottoStoricoDto = prodottoStoricoMapper.entityToDto(prodottoStoricoMapper.toProdottoStorico(prodotto));
        prodottoStoricoDto.setDataCreazione(LocalDateTime.now());
        if (prodottoDto.getFornitore() != null)
            prodottoStoricoDto.setFornitore(fornitoreService.inserisciModificaFornitore(prodottoDto.getFornitore()));
        prodottoStoricoRepository.save(prodottoStoricoMapper.dtoToEntity(prodottoStoricoDto));
    }

    public List<ProdottoStoricoDto> getStoricoProdottoDaCodiceProdotto(String codiceProdotto) {
        return prodottoStoricoRepository.findByCodiceProdotto(codiceProdotto).stream().map(prodottoStoricoMapper::entityToDto).collect(Collectors.toList());

    }

    public List<String> getCodiceProdotto() {
        return prodottoStoricoRepository.findDistinctCodiceProdotto().stream().toList();
    }


}
