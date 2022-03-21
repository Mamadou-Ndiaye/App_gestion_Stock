package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.CommandeClientDto;
import sn.ucad.gestionstock.dto.LigneCommandeClientDto;
import sn.ucad.gestionstock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);

    CommandeClientDto  findById(Long id);

    List<CommandeClientDto> findAll();

   CommandeClientDto findByCode(String code);

    void   deleteById(Long id);

    CommandeClientDto updateEtatCommandeClient(Long id, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite);

    CommandeClientDto updateClient(Long idCommande, Long idClient);

    CommandeClientDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle);

    // Delete article == delete ligneCommandeClient
    CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande);

    List<LigneCommandeClientDto>  findAllLignesCommandeClientByCommandeClientId(Long idCommande);



}
