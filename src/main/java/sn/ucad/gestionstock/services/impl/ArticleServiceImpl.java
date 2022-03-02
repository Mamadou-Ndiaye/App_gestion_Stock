package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.gestionstock.dto.ArticleDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.repository.ArticleRepository;
import sn.ucad.gestionstock.services.ArticleService;
import sn.ucad.gestionstock.validator.ArticleValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    private  ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        List<String>  errors = ArticleValidator.validate(articleDto);

        if (!errors.isEmpty())
        {
               log.error("Article is not valide  {}", articleDto);
               throw  new InvalidEntityException("L'Article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID);
        }

        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
    }

    @Override
    public ArticleDto findById(Long id) {
        if (id== null)
        {
            log.error("Article is NULL");
            return  null;
        }

        Optional<Article> article = articleRepository.findById(id);

        //ArticleDto articleDto = ArticleDto.fromEntity(article.get());

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()-> new EntityNotFoundException("Aucun article avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (codeArticle == null)
        {
            log.error("Article code  is NULL");
            return  null;
        }
        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);

        //ArticleDto articleDto = ArticleDto.fromEntity(article.get());

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()-> new EntityNotFoundException("Aucun article avec codeArticle = "+ codeArticle + "n'a ete trouve dans la base de donnees",ErrorCodes.ARTICLE_NOT_FOUND));    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id== null)
        {
            log.error("Article is NULL");
            return;
        }

        articleRepository.deleteById(id);
    }
}
