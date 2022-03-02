package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Fournisseur;


@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
}
