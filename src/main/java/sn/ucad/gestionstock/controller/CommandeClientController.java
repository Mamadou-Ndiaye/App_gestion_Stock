package sn.ucad.gestionstock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.CommandeClientApi;
import sn.ucad.gestionstock.dto.CommandeClientDto;
import sn.ucad.gestionstock.dto.LigneCommandeClientDto;
import sn.ucad.gestionstock.model.EtatCommande;
import sn.ucad.gestionstock.services.CommandeClientService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController  implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }



    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        return commandeClientService.save(commandeClientDto);
    }

    @Override
    public CommandeClientDto updateEtatCommande(Long id, EtatCommande etatCommande) {
        return  commandeClientService.updateEtatCommandeClient(id,etatCommande);
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite) {
        return commandeClientService.updateQuantiteCommande(idCommande,idLigneCommande,quantite);
    }

    @Override
    public CommandeClientDto updateClient(Long idCommande, Long idClient) {
        return commandeClientService.updateClient(idCommande,idClient);
    }


    @Override
    public CommandeClientDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle) {
        return commandeClientService.updateArticle(idCommande,idLigneCommande,newIdArticle);
    }

    @Override
    public CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) {
        return commandeClientService.deleteArticle(idCommande,idLigneCommande);
    }

    @Override
    public List<LigneCommandeClientDto> findAllLignesCommandeClientByCommandeClientId(Long idCommande) {
        return  commandeClientService.findAllLignesCommandeClientByCommandeClientId(idCommande);
    }

    @Override
    public CommandeClientDto findById(Long id) {
        return commandeClientService.findById(id);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientService.findAll();
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return commandeClientService.findByCode(code);
    }

    @Override
    public void deleteById(Long id) {
           commandeClientService.deleteById(id);
    }
}
