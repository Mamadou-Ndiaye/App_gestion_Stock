package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.Roles;

public interface RolesRepository extends JpaRepository<Roles,Long> {
}
