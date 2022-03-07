package sn.ucad.gestionstock.dto;

import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.model.Category;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@Data
@Builder
public class ArticleDto {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long idArticle;

    /* @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHT;  // prix Unitaire Hors TVA

    private BigDecimal tauxTva; // taux TVA pour Chaqe Article

    private BigDecimal prixUnitaireTTC;

    private String photo;

    private Long idEntreprise;

    // @JoinColumn(name = "idCategory")
    //@ManyToOne
    private CategoryDto  categoryDto;

    public static ArticleDto fromEntity(Article article)
    {
        if ( article == null)
        {
            return  null;
            // TODO
        }

        return ArticleDto.builder()
                .idArticle(article.getIdArticle())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHT(article.getPrixUnitaireHT())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTTC(article.getPrixUnitaireTTC())
                .photo(article.getPhoto())
                .idEntreprise(article.getIdEntreprise())
                .categoryDto(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }

    public  static Article toEntity(ArticleDto articleDto)
    {
        if ( articleDto == null)
        {
            return  null ;
            // TODO
        }

        return  Article.builder()
                .idArticle(articleDto.getIdArticle())
                .codeArticle(articleDto.getCodeArticle())
                .designation(articleDto.getDesignation())
                .prixUnitaireHT(articleDto.getPrixUnitaireHT())
                .tauxTva(articleDto.getTauxTva())
                .prixUnitaireTTC(articleDto.getPrixUnitaireTTC())
                .photo(articleDto.getPhoto())
                .idEntreprise(articleDto.getIdEntreprise())
                .category(CategoryDto.toEntity(articleDto.getCategoryDto()))
                .build();
    }
}
