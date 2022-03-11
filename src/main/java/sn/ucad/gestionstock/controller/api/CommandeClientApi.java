package sn.ucad.gestionstock.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.ArticleDto;
import sn.ucad.gestionstock.dto.CategoryDto;
import sn.ucad.gestionstock.dto.ClientDto;
import sn.ucad.gestionstock.dto.CommandeClientDto;

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;


@Api(APP_ROOT + "/commandeclients")
public interface CommandeClientApi {

    @ApiOperation(value = "Enregister  une commande client !", notes = "Cette methode permet d enregistrer ou modifier une commande client",response = CommandeClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L Objet commande client crée / modifié"), @ApiResponse(code = 404, message = "L Objet commande client n 'est pas valide")})
    @PostMapping(value =APP_ROOT + "/commandeclients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);

    @ApiOperation(value = "rechercher  une commande client !", notes = "Cette methode permet de rechercher une commande client dans la BDD avec son ID",response = CommandeClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La commande client a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun Commande client n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value =  APP_ROOT+ "/commandeclients/{idCmdClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto  findById(@PathVariable("idCmdClient") Long id);

    @ApiOperation(value = "La liste des commandes clients  !", notes = "Cette methode permet de renvoyer la liste des commandes clients",responseContainer ="List<CommandeClientDto>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La liste des commandes clients"),@ApiResponse(code = 404, message = "Aucune commande client n'a été trouvé dans la BDD")})
    @GetMapping(value =  APP_ROOT+ "/commandeclients/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientDto> findAll();

    @ApiOperation(value = "rechercher  une commande client avec son code !", notes = "Cette methode permet de rechercher une une commande client  dans la BDD avec son code",response = CommandeClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La commande client  a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucune commande client  n'a été trouvé dans la BDD avec le code fournie")})
    @GetMapping(value = APP_ROOT + "/commandeclients/{codeCmdClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findByCode(@PathVariable("codeCmdClient") String code);


    @ApiOperation(value = "Supprimer une commande client !", notes = "Cette methode permet de rechercher une commande client dans la BDD avec son ID",response = CommandeClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La commande client a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucune commande client n'a été trouvé dans la BDD avec l ID fournie")})
    @DeleteMapping(value = APP_ROOT + "/commandeclients/delete/{idCmdClient}")
    void   deleteById(@PathVariable("idCmdClient")  Long id);
}
