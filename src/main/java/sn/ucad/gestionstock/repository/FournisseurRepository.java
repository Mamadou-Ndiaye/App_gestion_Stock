package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
}
