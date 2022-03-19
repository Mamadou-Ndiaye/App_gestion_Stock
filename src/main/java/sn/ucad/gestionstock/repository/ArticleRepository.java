package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Article;


import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ArticleRepository  extends JpaRepository<Article,Long> {


   Optional<Article> findArticleByCodeArticle(String codeArticle);

   List<Article>  findAllByCategoryId(Long idCategory);
}
