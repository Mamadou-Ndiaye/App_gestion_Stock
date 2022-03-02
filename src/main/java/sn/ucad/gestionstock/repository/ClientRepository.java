package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    
}
