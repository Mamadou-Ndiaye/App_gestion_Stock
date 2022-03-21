package sn.ucad.gestionstock.validator;

import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.CommandeClientDto;
import sn.ucad.gestionstock.dto.MvtStkDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {


    public static List<String> validate(MvtStkDto mvtStkDto) {
        List<String> errors = new ArrayList<>();

        if (mvtStkDto == null)
        {
            errors.add("Veuillez renseigner l' article du mvtStk");
            errors.add("Veuillez renseigner la date de la mvtStk");
            errors.add("Veuillez renseigner la quantité du mvtStk");

            return  errors;
        }
        if (mvtStkDto.getDateMvt() == null ){
            errors.add("Veuillez renseigner la date de la mvtStk");
        }
        if (mvtStkDto.getQuantite() == null ||  mvtStkDto.getQuantite().compareTo(BigDecimal.ZERO) == 0 ){
            errors.add("Veuillez renseigner la quantité du mvtStk");
        }
        if (mvtStkDto.getArticleDto() == null ||  mvtStkDto.getArticleDto().getIdArticle() == null ){
            errors.add("Veuillez renseigner l' article du mvtStk");
        }
        if (mvtStkDto.getArticleDto() == null ||  mvtStkDto.getArticleDto().getIdArticle() == null ){
            errors.add("Veuillez renseigner l' article du mvtStk");
        }
        if (StringUtils.hasLength(mvtStkDto.getTypeMvt().name())){
            errors.add("Veuillez renseigner le type du mvtStk");
        }
        return  errors;
    }
}
