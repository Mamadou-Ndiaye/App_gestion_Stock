package sn.ucad.gestionstock.services;


import sn.ucad.gestionstock.dto.CommandeFournisseurDto;

import sn.ucad.gestionstock.dto.LigneCommandeFournisseurDto;
import sn.ucad.gestionstock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    CommandeFournisseurDto  findById(Long id);

    List<CommandeFournisseurDto> findAll();

    CommandeFournisseurDto findByCode(String code);

    void   deleteById(Long id);

    CommandeFournisseurDto updateEtatCommandeClient(Long id, EtatCommande etatCommande);

    CommandeFournisseurDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite);

    CommandeFournisseurDto updateFournisseur(Long idCommande, Long idFournisseur);

    CommandeFournisseurDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle);

    // Delete article == delete ligneCommandeClient
    CommandeFournisseurDto deleteArticle(Long idCommande, Long idLigneCommande);

    List<LigneCommandeFournisseurDto>  findAllLignesCommandeFournisseurByCommandeFournisseurId(Long idCommande);
}
