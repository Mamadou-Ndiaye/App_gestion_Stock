package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.model.MvtStk;



@Repository
public interface MvtStkRepository extends JpaRepository<MvtStk,Long> {
}

