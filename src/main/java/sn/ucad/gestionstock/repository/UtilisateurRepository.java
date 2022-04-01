package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Utilisateur;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    Optional<Utilisateur> findByMail(String mail);

    Optional<Utilisateur> findByMailOrPrenom(String mail);



    List<Utilisateur> findByPrenom(String prenom);

}

