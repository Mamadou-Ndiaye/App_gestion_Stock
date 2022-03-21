package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.CommandeFournisseur;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur,Long> {

    Optional<CommandeFournisseur> findByCode(String code);

    List<CommandeFournisseur> findAllByFournisseurId(Long id);
}
