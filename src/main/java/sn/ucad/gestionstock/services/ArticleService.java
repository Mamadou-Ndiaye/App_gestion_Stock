package sn.ucad.gestionstock.services;

import org.springframework.stereotype.Service;
import sn.ucad.gestionstock.dto.ArticleDto;

import java.util.List;


@Service
public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Long id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto>  findAll();

    void  deleteById(Long id);
}
