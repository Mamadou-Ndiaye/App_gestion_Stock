package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
