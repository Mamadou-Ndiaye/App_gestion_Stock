package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.CommandeClientDto;
import sn.ucad.gestionstock.dto.CommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = new ArrayList<>();

        if(commandeFournisseurDto == null)
        {
            errors.add("Veuillez renseigner le code de la commande");
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez renseigner le code de l etat de la commande");
            errors.add("Veuillez renseigner le client de la commande");
            return  errors;
        }
        if (StringUtils.hasLength(commandeFournisseurDto.getCode())){
            errors.add("Veuillez renseigner le code de la commande");
        }
        if (commandeFournisseurDto.getDateCommande() == null ){
            errors.add("Veuillez renseigner la date de la commande");
        }
        if (StringUtils.hasLength(commandeFournisseurDto.getEtatCommande().toString())){
            errors.add("Veuillez renseigner le code de l etat de la commande");
        }
        if (commandeFournisseurDto.getFournisseurDto() == null || commandeFournisseurDto.getFournisseurDto().getIdFournisseur() == null){
            errors.add("Veuillez renseigner le fournisseur de la commande");
        }

        return  errors;
    }
}
