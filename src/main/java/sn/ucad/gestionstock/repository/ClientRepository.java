package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Client;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client>  findByPrenom(String prenom);

    Optional<Client>   findByMail(String mail);
}
