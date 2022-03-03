package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Fournisseur;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {

    Optional<Fournisseur> findByMail(String mail);

    List<Fournisseur> findByPrenom(String prenom);
}
