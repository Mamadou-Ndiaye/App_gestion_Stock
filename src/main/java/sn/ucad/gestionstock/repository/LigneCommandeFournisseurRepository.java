package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.LigneCommandeFournisseur;

import java.util.List;


@RepositoryRestResource
public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur,Long> {

   List<LigneCommandeFournisseur> findAllByArticleId(Long idArticle);
}
