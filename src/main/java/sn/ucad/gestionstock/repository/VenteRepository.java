package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.Vente;

public interface VenteRepository extends JpaRepository<Vente,Long> {
}
