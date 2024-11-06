package org.example.gestionale_be.Mapper;

import org.example.gestionale_be.Dto.ProdottoDto;
import org.example.gestionale_be.Entity.Prodotto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",uses = ProdottoMapper.class)
public interface ProdottoMapper {


    Prodotto dtoToEntity (ProdottoDto prodottoDto);

    ProdottoDto entityToDto (Prodotto prodotto);
}
