package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.CommandeClientDto;
import sn.ucad.gestionstock.dto.VenteDto;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {


    public static List<String> validate(VenteDto venteDto) {
        List<String> errors = new ArrayList<>();

        if(venteDto == null)
        {
            errors.add("Veuillez renseigner le code de la commande");
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez renseigner le code de l etat de la commande");
            errors.add("Veuillez renseigner le client de la commande");
            return  errors;
        }
        if (StringUtils.hasLength(venteDto.getCode())){
            errors.add("Veuillez renseigner le code de la commande");
        }
        if (venteDto.getDateVente() == null ){
            errors.add("Veuillez renseigner la date de la vente");
        }


        return  errors;
    }
}
