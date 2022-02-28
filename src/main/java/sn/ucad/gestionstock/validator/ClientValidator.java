package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto clientDto)
    {
        List<String> errors = new ArrayList<>();

        if (clientDto == null)
        {
            errors.add("Veuillez renseigner le nom de l'utilisateur");
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
            errors.add("Veuillez renseigner le numero de téléphone du l'utilisateur");
            return errors;
        }
        if ( !StringUtils.hasLength(clientDto.getNom()))
        {
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }
        if (!StringUtils.hasLength(clientDto.getPrenom()))
        {
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
        }
        if (!StringUtils.hasLength(clientDto.getMail()))
        {
            errors.add("Veuillez renseigner l'email de l'utilisateur");
        }
        if (!StringUtils.hasLength(clientDto.getNumTel()))
        {
            errors.add("Veuillez renseigner le numero de téléphone du l'utilisateur");
        }

        return  errors;
    }
}
