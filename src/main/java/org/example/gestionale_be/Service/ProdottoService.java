package org.example.gestionale_be.Service;

import lombok.Data;
import org.example.gestionale_be.Dto.ProdottoDto;
import org.example.gestionale_be.Eccezioni.NotFoundException;
import org.example.gestionale_be.Mapper.ProdottoMapper;
import org.example.gestionale_be.Repository.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ProdottoService {

    private final ProdottoRepository prodottoRepository;

    private final ProdottoMapper prodottoMapper;

    public ProdottoDto getProdotto(Long id) {
        return prodottoRepository.findById(id)
                .map(prodottoMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException("Prodotto non trovato per l'Id : " + id));

    }

    public List<ProdottoDto> getProdotti() {
        return prodottoRepository.findAll().stream().map(prodottoMapper::entityToDto).collect(Collectors.toList());
    }

    public ProdottoDto inserisciProdotto(ProdottoDto prodottoDto) {
        return prodottoMapper.entityToDto(prodottoRepository.save(prodottoMapper.dtoToEntity(prodottoDto)));
    }

    public ProdottoDto modificaProdotto(ProdottoDto prodottoDto) {

        if(prodottoRepository.existsById(prodottoDto.getId()))
            return prodottoMapper.entityToDto(prodottoRepository.save(prodottoMapper.dtoToEntity(prodottoDto)));
        else
            throw new NotFoundException("Prodotto non trovato");

    }
}