package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Roles;



@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
}

