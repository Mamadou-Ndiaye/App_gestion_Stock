package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
}
