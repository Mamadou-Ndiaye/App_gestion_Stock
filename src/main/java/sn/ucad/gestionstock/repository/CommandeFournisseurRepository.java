package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.CommandeFournisseur;


@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur,Long> {
}
