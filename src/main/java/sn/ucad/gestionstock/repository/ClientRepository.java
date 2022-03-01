package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
    
}
