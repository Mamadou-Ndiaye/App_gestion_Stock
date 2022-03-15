package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.UtilisateurDto;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto)
    {
        List<String> errors = new ArrayList<>();


       /*if (utilisateurDto == null)
        {
            errors.add("Veuillez renseigner le nom de l'utilisateur");
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
            return errors;
        }
        if ( !StringUtils.hasLength(utilisateurDto.getNom()))
        {
           errors.add("Veuillez renseigner le nom de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom()))
        {
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMail()))
        {
            errors.add("Veuillez renseigner l'email de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse()))
        {
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        }
        if (utilisateurDto.getDateDeNaissance()== null)
        {
            errors.add("Veuillez renseigner la date de naissance de l'utilisateur");
        }*/
        if (utilisateurDto.getAdresseDto()== null)
        {
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
        }
        else {

            if (!StringUtils.hasLength(utilisateurDto.getAdresseDto().getAdresse1()))
            {
                errors.add(" Le champs 'Adresse1 ' est obligatoire");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresseDto().getVille()))
            {
                errors.add(" Le champs 'Ville ' est obligatoire ");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresseDto().getCodePostal()))
            {
                errors.add(" Le champs 'Code Postal ' est obligatoire ");
            }
            if (!StringUtils.hasLength(utilisateurDto.getAdresseDto().getPays()))
            {
                errors.add(" Le champs 'Pays ' est obligatoire ");
            }

        }

        return  errors;

    }
}
