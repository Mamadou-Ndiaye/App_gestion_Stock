package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.ucad.gestionstock.model.Article;

import java.util.List;

public interface ArticleRepository  extends JpaRepository<Article,Long> {

    @Query("")
    List<Article>  findByCustomQuery();
}
