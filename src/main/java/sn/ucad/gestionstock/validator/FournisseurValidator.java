package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.FournisseurDto;


import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validate(FournisseurDto fournisseurDto)
    {
        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null)
        {
            errors.add("Veuillez renseigner le nom du fournisser");
            errors.add("Veuillez renseigner le prenom du fournisser");
            errors.add("Veuillez renseigner l'adresse du fournisser");
            errors.add("Veuillez renseigner le numero de téléphone du fournisser");
            return errors;
        }
        if ( !StringUtils.hasLength(fournisseurDto.getNom()))
        {
            errors.add("Veuillez renseigner le nom  du fournisser");
        }
        if (!StringUtils.hasLength(fournisseurDto.getPrenom()))
        {
            errors.add("Veuillez renseigner le prenom du fournisser");
        }
        if (!StringUtils.hasLength(fournisseurDto.getMail()))
        {
            errors.add("Veuillez renseigner l'email du fournisser");
        }
        if (!StringUtils.hasLength(fournisseurDto.getNumTel()))
        {
            errors.add("Veuillez renseigner le numero de téléphone du fournisser");
        }

        return  errors;
    }
}
