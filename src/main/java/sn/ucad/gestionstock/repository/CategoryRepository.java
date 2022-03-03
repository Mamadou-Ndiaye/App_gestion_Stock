package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.Category;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category>  findByCode(String code);

    List<Category>     findByDesignation(String designation);
}
