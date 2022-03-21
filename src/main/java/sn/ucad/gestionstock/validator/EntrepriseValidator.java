package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.EntrepriseDto;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto)
    {
        List<String> errors = new ArrayList<>();


        if (entrepriseDto == null)
        {
            errors.add("Veuillez renseigner le nom de l entreprise");
            errors.add("Veuillez renseigner le mot de passe de l entreprise");
            errors.add("Veuillez renseigner l'adresse de l entreprise");
            return errors;
        }
        if ( !StringUtils.hasLength(entrepriseDto.getNom()))
        {
            errors.add("Veuillez renseigner le nom de l entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getMail()))
        {
            errors.add("Veuillez renseigner l'email de l entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getNumTel()))
        {
            errors.add("Veuillez renseigner le numero de telephone de l entreprise");
        }
       /* if (entrepriseDto.getAdresseDto()== null)
        {
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
        }
        else {

            if (!StringUtils.hasLength(entrepriseDto.getAdresseDto().getAdresse1()))
            {
                errors.add(" Le champs 'Adresse1 ' est obligatoire");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresseDto().getVille()))
            {
                errors.add(" Le champs 'Ville ' est obligatoire ");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresseDto().getCodePostal()))
            {
                errors.add(" Le champs 'Code Postal ' est obligatoire ");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresseDto().getPays()))
            {
                errors.add(" Le champs 'Pays ' est obligatoire ");
            }

        }
*/

        errors.addAll(AdresseValidator.validate(entrepriseDto.getAdresseDto()));
        return  errors;

    }
}
