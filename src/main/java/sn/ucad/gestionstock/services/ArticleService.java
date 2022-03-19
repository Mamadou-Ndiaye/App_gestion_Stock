package sn.ucad.gestionstock.services;

import org.springframework.stereotype.Service;
import sn.ucad.gestionstock.dto.ArticleDto;
import sn.ucad.gestionstock.dto.LigneCommandeClientDto;
import sn.ucad.gestionstock.dto.LigneCommandeFournisseurDto;
import sn.ucad.gestionstock.dto.LigneVenteDto;

import java.util.List;


@Service
public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Long id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto>  findAll();

    void  deleteById(Long id);

    List<LigneVenteDto>  findHistoriqueVente(Long idArticle);

    List<LigneCommandeClientDto>  findHistoriqueCommandeClient(Long idArticle);

    List<LigneCommandeFournisseurDto>  findHistoriqueCommandeFournisseur(Long idArticle);

    List<ArticleDto>  findAllArticleByIdCategory(Long idCategory);

}
