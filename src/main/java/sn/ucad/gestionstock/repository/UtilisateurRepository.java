package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
}