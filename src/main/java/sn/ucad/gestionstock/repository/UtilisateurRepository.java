package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Utilisateur;



@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
}

