package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.CommandeClient;

public interface CommandeClientRepository extends JpaRepository<CommandeClient,Long> {
}
