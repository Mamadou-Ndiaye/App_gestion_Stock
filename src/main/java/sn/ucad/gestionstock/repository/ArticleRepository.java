package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.ucad.gestionstock.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository  extends JpaRepository<Article,Long> {


    Optional<Article> findArticleByCodeArticle(String codeArticle);
}
