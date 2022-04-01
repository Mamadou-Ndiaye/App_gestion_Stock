package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Roles;



@RepositoryRestResource
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByRoleName(String roleName);
}

