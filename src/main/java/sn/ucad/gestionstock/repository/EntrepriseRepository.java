package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Entreprise;

import java.util.List;
import java.util.Optional;


@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {

    Optional<Entreprise> findByNom(String nom);
}
