package sn.ucad.gestionstock.dto;

import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.model.Category;
import sn.ucad.gestionstock.model.CommandeFournisseur;
import sn.ucad.gestionstock.model.LigneCommandeFournisseur;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@Data
@Builder
public class LigneCommandeFournisseurDto {

    private Long idLigneCdeFrs;

    //@ManyToOne
    //@JoinColumn(name = "idArticle")
    private ArticleDto articleDto;

    //@ManyToOne
    // @JoinColumn(name = "idCommandeFournisseur")
    private CommandeFournisseurDto commandeFournisseurDto;

    private BigDecimal quantite;

    private Long idEntreprise;

    public static LigneCommandeFournisseurDto  fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur)
    {
        if ( ligneCommandeFournisseur == null)
        {
            return  null;
            // TODO
        }

        return  LigneCommandeFournisseurDto.builder()
                .idLigneCdeFrs(ligneCommandeFournisseur.getIdLigneCdeFrs())
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .quantite(ligneCommandeFournisseur.getQuantite())
                .articleDto(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                //.commandeFournisseurDto(ligneCommandeFournisseur.getCommandeFournisseur())
                .build();
    }

    public static LigneCommandeFournisseur toEntity( LigneCommandeFournisseurDto ligneCommandeFournisseurDto)
    {
        if( ligneCommandeFournisseurDto == null)
        {
             return  null;
             // TODO
        }

        return  LigneCommandeFournisseur.builder()
                .idLigneCdeFrs(ligneCommandeFournisseurDto.getIdLigneCdeFrs())
                .quantite(ligneCommandeFournisseurDto.getQuantite())
                .article(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticleDto()))
                .idEntreprise(ligneCommandeFournisseurDto.getIdEntreprise())
                .build();
    }
}
