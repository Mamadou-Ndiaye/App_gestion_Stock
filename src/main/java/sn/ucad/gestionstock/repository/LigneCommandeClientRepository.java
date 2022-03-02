package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.LigneCommandeClient;


@Repository
public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient,Long> {
}
