package sn.ucad.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.model.Category;
import sn.ucad.gestionstock.model.CommandeClient;
import sn.ucad.gestionstock.model.LigneCommandeClient;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@Data
@Builder
public class  LigneCommandeClientDto {


    private Long idLigneCdeClt;

    //@JoinColumn(name = "idArticle")
    //@ManyToOne
    private ArticleDto articleDto;

    @JsonIgnore
    private CommandeClientDto commandeClientDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Long idEntreprise;

    public static LigneCommandeClientDto  fromEntity(LigneCommandeClient ligneCommandeClient)
    {
        if ( ligneCommandeClient == null)
        {
            return  null;
            // TODO
        }

        return  LigneCommandeClientDto.builder()
                .idLigneCdeClt(ligneCommandeClient.getIdLigneCdeClt())
                .articleDto(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto)
    {
         if ( ligneCommandeClientDto == null)
         {
             return  null;
             // TODO
         }

         return  LigneCommandeClient.builder()
                 .idLigneCdeClt(ligneCommandeClientDto.getIdLigneCdeClt())
                 .article(ArticleDto.toEntity(ligneCommandeClientDto.getArticleDto()))
                 .quantite(ligneCommandeClientDto.getQuantite())
                 .prixUnitaire(ligneCommandeClientDto.getPrixUnitaire())
                 .build();
    }


}
