package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Vente;

import java.util.Optional;


@RepositoryRestResource
public interface VenteRepository extends JpaRepository<Vente,Long> {

    Optional<Vente> findByCode(String code);

}

