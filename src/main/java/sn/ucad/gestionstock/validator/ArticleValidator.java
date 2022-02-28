package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public static List<String>  validate(ArticleDto articleDto)
    {
        List<String> errors = new ArrayList<>();


        if (articleDto == null)
        {
            errors.add("Veuillez renseigner le Code de l'Article");
            errors.add("Veuillez renseigner le prix Unitaire de l article");
            errors.add("Veuillez renseigner le taux de tva de l article");
            errors.add("Veuillez renseigner le prix unitaire TTC de l article");
            errors.add("Veuillez selectionner la categorie de l'article ");
            return errors;
        }
        if (StringUtils.hasLength(articleDto.getCodeArticle()))
        {
            errors.add("Veuillez renseigner le Code de l'Article");
        }
        if (articleDto.getPrixUnitaireHT()== null)
        {
            errors.add("Veuillez renseigner le prix Unitaire de l article");
        }
        if (articleDto.getTauxTva()== null)
        {
            errors.add("Veuillez renseigner le taux de tva de l article");
        }
        if (articleDto.getPrixUnitaireTTC()== null)
        {
            errors.add("Veuillez renseigner le prix unitaire TTC de l article");
        }
        if (articleDto.getCategoryDto()== null)
        {
            errors.add("Veuillez selectionner la categorie de l'article ");
        }



        return  errors;
    }
}


