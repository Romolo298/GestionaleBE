package org.example.gestionale_be.Mapper;

import org.example.gestionale_be.Dto.FornitoreDto;
import org.example.gestionale_be.Entity.Fornitore;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FornitoreMapper {

    Fornitore dtoToEntity(FornitoreDto fornitoreDto);

    FornitoreDto entityToDto(Fornitore fornitore);
}
