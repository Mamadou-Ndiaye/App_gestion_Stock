package sn.ucad.gestionstock.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.CommandeClientDto;
import sn.ucad.gestionstock.dto.CommandeFournisseurDto;
import sn.ucad.gestionstock.dto.LigneCommandeFournisseurDto;
import sn.ucad.gestionstock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;


@Api(APP_ROOT + "/commandefournisseurs")
public interface CommandeFournisseurApi {

    @ApiOperation(value = "Enregister  une commande fournissseur !", notes = "Cette methode permet d enregistrer ou modifier une commande fournisseur",response = CommandeClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L Objet commande fournisseur crée / modifié"), @ApiResponse(code = 404, message = "L Objet commande fournisseur n 'est pas valide")})
    @PostMapping(value =APP_ROOT + "/commandefournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto save(@RequestBody  CommandeFournisseurDto commandeFournisseurDto);

    @ApiOperation(value = "rechercher  une commande fournissseur !", notes = "Cette methode permet de rechercher une commande fournisseur dans la BDD avec son ID",response = CommandeFournisseurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La commande fournisseur a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun Commande fournisseur n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value =  APP_ROOT+ "/commandefournisseurs/{idCmdFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto  findById(@PathVariable("idCmdFournisseur") Long id);

    @ApiOperation(value = "La liste des commandes fournisseurs  !", notes = "Cette methode permet de renvoyer la liste des commandes fournisseurs",responseContainer ="List<CommandeFournisseurDto>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La liste des commandes fournisseur"),@ApiResponse(code = 404, message = "Aucune commande fournisseur n'a été trouvé dans la BDD")})
    @GetMapping(value =  APP_ROOT+ "/commandefournisseurs/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeFournisseurDto> findAll();


    @ApiOperation(value = "rechercher  une commande fournisseur avec son code !", notes = "Cette methode permet de rechercher une une commande fournisseur dans la BDD avec son code",response = CommandeFournisseurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La commande fournisseur  a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucune commande fournisseur  n'a été trouvé dans la BDD avec le code fournie")})
    @GetMapping(value = APP_ROOT + "/commandefournisseurs/{codeCmdFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findByCode(@PathVariable("codeCmdFournisseur") String code);


    @ApiOperation(value = "Supprimer une commande fournisseur !", notes = "Cette methode permet de rechercher une commande fournisseur dans la BDD avec son ID",response = CommandeFournisseurDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La commande fournisseur a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucune commande fournisseur n'a été trouvé dans la BDD avec l ID fournie")})
    @DeleteMapping(value = APP_ROOT + "/commandefournisseurs/delete/{idCmdFournisseur}")
    void   deleteById(@PathVariable("idCmdFournisseur") Long id);


    @PatchMapping(value =APP_ROOT + "/commandefourniseurs/update/etat/{idCommande}/{etatCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto updateEtatCommandeClient(@PathVariable("idCommande") Long id,@PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(value =APP_ROOT + "/commandefournisseurs/update/quantite/{idCommande}/{idLigneCommande}/{quantite}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto updateQuantiteCommande(@PathVariable("idCommande") Long idCommande,@PathVariable("idLigneCommande") Long idLigneCommande,@PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value =APP_ROOT + "/commandeclients/update/client/{idCommande}/{idFournisseur}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto updateFournisseur(@PathVariable("idCommande") Long idCommande, @PathVariable("idFournisseur") Long idFournisseur);

    @PatchMapping(value =APP_ROOT + "/commandefournisseurs/update/article/{idCommande}/{idLigneCommande}/{idLigneCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto updateArticle(@PathVariable("idCommande")Long idCommande,@PathVariable("idLigneCommande") Long idLigneCommande,@PathVariable("idLigneCommande") Long newIdArticle);

    // Delete article == delete ligneCommandeClient
    @DeleteMapping(value = APP_ROOT +"/commandefournisseurs/delete/article/{idCommande}/{idLigneCommande}")
    CommandeFournisseurDto deleteArticle(@PathVariable("idCommande") Long idCommande,@PathVariable("idLigneCommande") Long idLigneCommande);

    @GetMapping(value =  APP_ROOT+ "/commandefournisseurs/ligneCommande/{idCommande}" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeFournisseurDto>  findAllLignesCommandeFournisseurByCommandeFournisseurId(@PathVariable("idCommande") Long idCommande);
}
