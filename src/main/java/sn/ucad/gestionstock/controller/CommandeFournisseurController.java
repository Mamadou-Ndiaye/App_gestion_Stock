package sn.ucad.gestionstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.CommandeFournisseurApi;
import sn.ucad.gestionstock.dto.CommandeFournisseurDto;
import sn.ucad.gestionstock.dto.LigneCommandeFournisseurDto;
import sn.ucad.gestionstock.model.EtatCommande;
import sn.ucad.gestionstock.services.CommandeFournisseurService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController  implements CommandeFournisseurApi {

    private CommandeFournisseurService fournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        return fournisseurService.save(commandeFournisseurDto);
    }

    @Override
    public CommandeFournisseurDto findById(Long id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return   fournisseurService.findAll();
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return fournisseurService.findByCode(code);
    }

    @Override
    public void deleteById(Long id) {
               fournisseurService.deleteById(id);
    }

    @Override
    public CommandeFournisseurDto updateEtatCommandeClient(Long id, EtatCommande etatCommande) {
        return fournisseurService.updateEtatCommandeClient(id,etatCommande);
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite) {
        return fournisseurService.updateQuantiteCommande(idCommande,idLigneCommande,quantite);
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Long idCommande, Long idFournisseur) {
        return fournisseurService.updateFournisseur(idCommande,idFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle) {
        return fournisseurService.updateArticle(idCommande,idLigneCommande,newIdArticle);
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Long idCommande, Long idLigneCommande) {
        return fournisseurService.deleteArticle(idCommande,idLigneCommande);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLignesCommandeFournisseurByCommandeFournisseurId(Long idCommande) {
        return fournisseurService.findAllLignesCommandeFournisseurByCommandeFournisseurId(idCommande);
    }
}
