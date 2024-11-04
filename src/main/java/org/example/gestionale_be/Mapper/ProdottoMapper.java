package org.example.gestionale_be.Mapper;

import org.example.gestionale_be.Dto.ProdottoDto;
import org.example.gestionale_be.Entity.Prodotto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = ProdottoMapper.class)
public interface ProdottoMapper {

    @Mapping(target = "fornitore", source = "fornitoreDto")
    Prodotto dtoToEntity (ProdottoDto prodottoDto);
    @Mapping(target = "fornitoreDto", source = "fornitore")
    ProdottoDto entityToDto (Prodotto prodotto);
}
