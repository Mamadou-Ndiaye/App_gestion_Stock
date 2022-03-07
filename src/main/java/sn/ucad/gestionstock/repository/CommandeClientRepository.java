package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sn.ucad.gestionstock.model.CommandeClient;

import java.util.Optional;


@RepositoryRestResource
public interface CommandeClientRepository extends JpaRepository<CommandeClient,Long> {


    Optional<CommandeClient>  findByCode(String code);
}
