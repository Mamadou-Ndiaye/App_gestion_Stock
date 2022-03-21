package sn.ucad.gestionstock.dto;

import lombok.*;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.model.Category;
import sn.ucad.gestionstock.model.LigneVente;
import sn.ucad.gestionstock.model.Vente;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


//@Data

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class LigneVenteDto {

    private Long idLigneVente;

    //@JoinColumn(name = "idArticle")
    //@ManyToOne
    private ArticleDto articleDto;

    private Long idEntreprise;

    private BigDecimal quantite;

    // @JoinColumn(name = "vente")
   // @ManyToOne
    private VenteDto venteDto;

    public static  LigneVenteDto  fromEntity(LigneVente ligneVente)
    {
        if ( ligneVente == null)
        {
             return  null;
             // TODO
        }



       return  LigneVenteDto.builder()
                .idLigneVente(ligneVente.getIdLigneVente())
               .idEntreprise(ligneVente.getIdEntreprise())
               .quantite(ligneVente.getQuantite())
                .articleDto(ArticleDto.fromEntity(ligneVente.getArticle()))
                .build();

    }

    public static LigneVente toEntity( LigneVenteDto ligneVenteDto)
    {
        if ( ligneVenteDto ==  null)
        {
            return null;
            // TODO
        }

        return  LigneVente.builder()
                 .idLigneVente(ligneVenteDto.getIdLigneVente())
                .idEntreprise(ligneVenteDto.getIdEntreprise())
                .quantite(ligneVenteDto.getQuantite())
                .article(ArticleDto.toEntity(ligneVenteDto.getArticleDto()))
                .build();
    }
}
