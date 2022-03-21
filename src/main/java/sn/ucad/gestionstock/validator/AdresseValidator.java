package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.AdresseDto;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {

    public static List<String> validate(AdresseDto adresseDto)
    {
        List<String> errors = new ArrayList<>();

        if ( adresseDto == null)
        {
            errors.add("Veuillez renseigner l adresse ");
            errors.add("Veuillez renseigner la ville ");
            errors.add("Veuillez renseigner le code postal ");
            errors.add("Veuillez renseigner le pays ");

        }

        if ( !StringUtils.hasLength(adresseDto.getAdresse1()))
        {
            errors.add("Veuillez renseigner l adresse ");
        }
        if ( !StringUtils.hasLength(adresseDto.getVille()))
        {
            errors.add("Veuillez renseigner la ville ");
        }
        if ( !StringUtils.hasLength(adresseDto.getCodePostal()))
        {
            errors.add("Veuillez renseigner le code postal ");
        }
        if ( !StringUtils.hasLength(adresseDto.getPays()))
        {
            errors.add("Veuillez renseigner le pays ");
        }

        return  errors;
    }
}
