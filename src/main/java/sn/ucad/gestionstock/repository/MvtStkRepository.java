package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ucad.gestionstock.model.MvtStk;

public interface MvtStkRepository extends JpaRepository<MvtStk,Long> {
}
