package sn.ucad.gestionstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ucad.gestionstock.dto.LigneCommandeClientDto;
import sn.ucad.gestionstock.model.LigneCommandeClient;

import java.util.List;


@Repository
public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient,Long> {

    List<LigneCommandeClient> findAllByCommandeClientId(Long idCommande);

    List<LigneCommandeClient> findAllByArticleId(Long idArticle);
}
