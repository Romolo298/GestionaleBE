package org.example.gestionale_be.Service;

import com.google.i18n.phonenumbers.NumberParseException;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.apache.coyote.BadRequestException;
import org.example.gestionale_be.Dto.ProdottoDto;
import org.example.gestionale_be.Eccezioni.NotFoundException;
import org.example.gestionale_be.Entity.Prodotto;
import org.example.gestionale_be.Mapper.ProdottoMapper;
import org.example.gestionale_be.Repository.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ProdottoService {

    private final ProdottoRepository prodottoRepository;

    private final ProdottoMapper prodottoMapper;

    private final FornitoreService fornitoreService;

    private final ProdottoStoricoService prodottoStoricoService;

    public ProdottoDto getProdotto(Long id) {
        return prodottoRepository.findById(id)
                .map(prodottoMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException("Prodotto non trovato per l'Id : " + id));

    }

    public List<ProdottoDto> getProdotti() {
        return prodottoRepository.findAll().stream().map(prodottoMapper::entityToDto).collect(Collectors.toList());
    }

    @Transactional
    public ProdottoDto inserisciProdotto(ProdottoDto prodottoDto) throws NumberParseException, BadRequestException {
        if (!prodottoRepository.existsByCodiceProdotto(prodottoDto.getCodiceProdotto())) {
            prodottoDto.setDataCreazione(LocalDate.now());

            if (prodottoDto.getFornitore() != null)
                inserimentoModificaFornitore(prodottoDto);

            return prodottoMapper.entityToDto(prodottoRepository.save(prodottoMapper.dtoToEntity(prodottoDto)));
        } else {
            Prodotto prodotto = prodottoRepository.findByCodiceProdotto(prodottoDto.getCodiceProdotto());
            prodottoStoricoService.inserimentoStorico(prodotto, prodottoDto);
            prodottoDto.setId(prodotto.getId());
            if (prodottoDto.getQuantitativo() != null)
                prodottoDto.setQuantitativo(prodottoDto.getQuantitativo() + prodotto.getQuantitativo());
            prodottoDto.setDataCreazione(prodotto.getDataCreazione());
            prodottoDto.setDataModifica(LocalDate.now());

            if (prodottoDto.getFornitore() != null)
                inserimentoModificaFornitore(prodottoDto);

            return prodottoMapper.entityToDto(prodottoRepository.save(prodottoMapper.dtoToEntity(prodottoDto)));
        }
    }

    public ProdottoDto modificaProdotto(ProdottoDto prodottoDto) {

        if (prodottoRepository.existsById(prodottoDto.getId()))
            return prodottoMapper.entityToDto(prodottoRepository.save(prodottoMapper.dtoToEntity(prodottoDto)));
        else
            throw new NotFoundException("Prodotto non trovato");

    }

    public String eliminaProdotto(Long id) {
        if (prodottoRepository.existsById(id)) {
            prodottoRepository.deleteById(id);
            return "Prodotto eliminato";
        }
        return "Nessun prodotto eliminato in quanto non presente";
    }

    private void inserimentoModificaFornitore(ProdottoDto prodottoDto) throws NumberParseException, BadRequestException {
        prodottoDto.setFornitore(fornitoreService.inserisciModificaFornitore(prodottoDto.getFornitore()));
    }
}
