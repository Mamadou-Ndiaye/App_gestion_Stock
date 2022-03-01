package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.LigneVente;

public interface LigneVenteRepository extends JpaRepository<LigneVente,Long> {
}
