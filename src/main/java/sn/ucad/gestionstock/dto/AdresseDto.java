package sn.ucad.gestionstock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ucad.gestionstock.model.Adresse;
import sn.ucad.gestionstock.model.Category;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdresseDto {

    private  String adresse1;

    private  String adresse2;

    private  String ville;

    private  String codePostal;

    private  String pays;

    public static AdresseDto fromEntity(Adresse adresse)
    {
        if (adresse == null)
         {
             return  null;
             // TODO an exception
        }

        return  AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .ville(adresse.getVille())
                .codePostal(adresse.getCodePostal())
                .pays(adresse.getPays())
                .build();
    }

    public static  Adresse toEntity(AdresseDto adresseDto)
    {
        if (adresseDto== null)
        {
            return  null;
        }

        return  Adresse.builder()
                .adresse1(adresseDto.getAdresse1())
                .adresse2(adresseDto.getAdresse2())
                .ville(adresseDto.getVille())
                .codePostal(adresseDto.getCodePostal())
                .pays(adresseDto.getPays())
                .build();
    }

}
