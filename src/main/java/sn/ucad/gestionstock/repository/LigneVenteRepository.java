package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.LigneVente;


@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente,Long> {
}
