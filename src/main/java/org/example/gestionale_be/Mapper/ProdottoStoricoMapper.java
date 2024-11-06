package org.example.gestionale_be.Mapper;

import org.example.gestionale_be.Dto.ProdottoDto;
import org.example.gestionale_be.Dto.ProdottoStoricoDto;
import org.example.gestionale_be.Entity.Prodotto;
import org.example.gestionale_be.Entity.ProdottoStorico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",uses = {FornitoreMapper.class, ProdottoMapper.class})
public interface ProdottoStoricoMapper {


    ProdottoStorico dtoToEntity (ProdottoStoricoDto prodottoStoricoDto);

    ProdottoStoricoDto entityToDto (ProdottoStorico prodottoStorico);

    @Mapping(target = "id", ignore = true)
    ProdottoStorico toProdottoStorico(Prodotto prodotto);
}
