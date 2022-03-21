package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.MvtStk;

import java.math.BigDecimal;
import java.util.List;


@RepositoryRestResource
public interface MvtStkRepository extends JpaRepository<MvtStk,Long> {

    @Query(" select  sum(m.quantite) from  MvtStk m where  m.article.idArticle = : idArticle ")
    BigDecimal stockReelArticle(@Param("idArticle") Long idArticle);

    List<MvtStk>  findAllByArticleId(Long idArticle);

}

