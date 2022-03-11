package sn.ucad.gestionstock.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.ArticleDto;
import sn.ucad.gestionstock.dto.CategoryDto;
import sn.ucad.gestionstock.dto.ClientDto;

import java.util.List;

import  static  sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;


@Api(APP_ROOT + "/clients")
public interface ClientApi {

    @ApiOperation(value = "Enregister  un client !", notes = "Cette methode permet d enregistrer ou modifier un client",response = ClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L Objet client crée / modifié"), @ApiResponse(code = 404, message = "L Objet client n 'est pas valide")})
    @PostMapping(value =APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto clientDto);

    @ApiOperation(value = "rechercher  un client !", notes = "Cette methode permet de rechercher un client dans la BDD avec son ID",response = ClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Le Client a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun client n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value =  APP_ROOT+ "/clients/{idClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto  findById(@PathVariable("idClient") Long id);

    @ApiOperation(value = "La liste des clients  !", notes = "Cette methode permet de renvoyer la liste des clients",responseContainer ="List<ClientDto>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La liste des clients"),@ApiResponse(code = 404, message = "Aucun client n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value =  APP_ROOT+ "/clients/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @ApiOperation(value = "rechercher  un client par mail !", notes = "Cette methode permet de rechercher un client dans la BDD par designation",response = ClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Le client a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun client n'a été trouvé dans la BDD par le mail fournie")})
    @GetMapping(value =  APP_ROOT+ "/clients/byMAil" , produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto  findByMail(@Param("mail") String mail);

    @ApiOperation(value = "rechercher  un client par prenom !", notes = "Cette methode permet de rechercher un client dans la BDD par designation",response = ClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Le client a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun client n'a été trouvé dans la BDD par le prenom fournie")})
    @GetMapping(value =  APP_ROOT+ "/clients/byPrenom" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto>  findByPrenom(@Param("prenom") String prenom);

    @ApiOperation(value = "Supprimer un client !", notes = "Cette methode permet de rechercher un client dans la BDD avec son ID",response = ClientDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Article a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun client n'a été trouvé dans la BDD avec l ID fournie")})
    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    void   deleteById(@PathVariable("idClient") Long id);
}
