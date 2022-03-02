package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.CommandeClient;


@Repository
public interface CommandeClientRepository extends JpaRepository<CommandeClient,Long> {
}
