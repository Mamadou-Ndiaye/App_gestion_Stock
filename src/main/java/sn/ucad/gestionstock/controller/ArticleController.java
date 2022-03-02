package sn.ucad.gestionstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.ArticleApi;
import sn.ucad.gestionstock.dto.ArticleDto;
import sn.ucad.gestionstock.services.ArticleService;

import java.util.List;


@RestController
public class ArticleController implements ArticleApi {

   //@Qualifier("nomDuService a appeler")
    private ArticleService articleService;


    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return articleService.save(articleDto);
    }

    @Override
    public ArticleDto findById(Long id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void deleteById(Long id) {
         articleService.deleteById(id);
    }
}
