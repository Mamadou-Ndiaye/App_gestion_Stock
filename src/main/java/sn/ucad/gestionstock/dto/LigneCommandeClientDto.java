package sn.ucad.gestionstock.dto;

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
public class LigneCommandeClientDto {


    private Long idLigneCdeClt;

    //@JoinColumn(name = "idArticle")
    //@ManyToOne
    private ArticleDto articleDto;

    //@ManyToOne
    // @JoinColumn(name = "idCommandeClient")
    private CommandeClientDto commandeClientDto;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneCommandeClientDto  fromEntity(LigneCommandeClient ligneCommandeClient)
    {
        if ( ligneCommandeClient == null)
        {
            return  null;
            // TODO
        }

        return  LigneCommandeClientDto.builder()
                .idLigneCdeClt(ligneCommandeClient.getIdLigneCdeClt())
              //  .articleDto(ligneCommandeClient.getArticle())
               // .commandeClientDto(ligneCommandeClient.getCommandeClient())
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
                 .quantite(ligneCommandeClientDto.getQuantite())
                 .prixUnitaire(ligneCommandeClientDto.getPrixUnitaire())
                 .build();
    }


}
