package org.example.gestionale_be.Service;

import com.google.i18n.phonenumbers.Phonenumber.*;
import lombok.Data;
import org.apache.coyote.BadRequestException;
import org.example.gestionale_be.Dto.FornitoreDto;

import org.example.gestionale_be.Eccezioni.NotFoundException;

import org.example.gestionale_be.Mapper.FornitoreMapper;

import org.example.gestionale_be.Repository.FornitoreRepository;
import com.google.i18n.phonenumbers.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class FornitoreService {

    private final FornitoreRepository fornitoreRepository;

    private final FornitoreMapper fornitoreMapper;

    public FornitoreDto getFornitore(Long id) {
        return fornitoreRepository.findById(id)
                .map(fornitoreMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException("Prodotto non trovato per l'Id : " + id));

    }

    public List<FornitoreDto> getFornitori() {
        return fornitoreRepository.findAll().stream().map(fornitoreMapper::entityToDto).collect(Collectors.toList());
    }

    public FornitoreDto inserisciFornitore(FornitoreDto fornitoreDto) throws NumberParseException, BadRequestException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        PhoneNumber phoneNumber = phoneUtil.parse(fornitoreDto.getNumeroTelefono(), "IT");
        if (phoneUtil.isValidNumber(phoneNumber)) {
                if(!fornitoreRepository.existsByRagioneSociale(fornitoreDto.getRagioneSociale()))
                    return fornitoreMapper.entityToDto(fornitoreRepository.save(fornitoreMapper.dtoToEntity(fornitoreDto)));
                else
                    throw new BadRequestException("Fornitore già censito");
        } else {
            throw new IllegalArgumentException("Numero di telefono non formattato correttamente");
        }
    }


    public FornitoreDto modificaFornitore(FornitoreDto fornitoreDto)
    {
        if(fornitoreRepository.existsById(fornitoreDto.getId()))
            return fornitoreMapper.entityToDto(fornitoreRepository.save(fornitoreMapper.dtoToEntity(fornitoreDto)));
        else
            throw new NotFoundException("Fornitore non trovato");
    }

    public String eliminaFornitore(Long id) {
        if(fornitoreRepository.existsById(id)) {
            fornitoreRepository.deleteById(id);
            return "Prodotto eliminato";
        }
        return  "Nessun prodotto eliminato in quanto non presente";
    }



}
