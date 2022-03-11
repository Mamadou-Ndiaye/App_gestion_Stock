package sn.ucad.gestionstock.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.ArticleDto;
import sn.ucad.gestionstock.dto.EntrepriseDto;

import java.util.List;
import  static  sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;



@Api(APP_ROOT + "/entreprises")
public interface EntrepriseApi {

    @ApiOperation(value = "Enregister  une entreprise !", notes = "Cette methode permet d enregistrer ou modifier un article",response = EntrepriseDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L Objet entreprise crée / modifié"), @ApiResponse(code = 404, message = "L Objet entreprise n 'est pas valide")})
    @PostMapping(value = APP_ROOT + "/entreprises/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(EntrepriseDto entrepriseDto);

    @ApiOperation(value = "rechercher  une entreprise !", notes = "Cette methode permet de rechercher une entreprise dans la BDD avec son ID",response = EntrepriseDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Entreprise a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucune entreprise n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}")
    EntrepriseDto findById(Long id);

    @PutMapping(value = APP_ROOT+ "/entreprises/{idEntreprise}")
    EntrepriseDto update(@PathVariable("idEntreprise") Long id,@RequestBody EntrepriseDto entrepriseDto);

    @ApiOperation(value = "La liste des entreprises  !", notes = "Cette methode permet de rechercher un article dans la BDD avec son ID",responseContainer ="List<EntrepriseDto>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Entreprise a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucune entreprise n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value = APP_ROOT+"/entreprise/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @GetMapping(value = APP_ROOT+"/entreprises/byNom")
    EntrepriseDto findByNom(@Param("nom") String nom);

    // List<EntrepriseDto> findByDesignation(String designation);

    @ApiOperation(value = "Supprimer une entreprise !", notes = "Cette methode permet de rechercher une entreprise dans la BDD avec son ID",response = EntrepriseDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Article a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucune entreprise n'a été trouvé dans la BDD avec l ID fournie")})
    @DeleteMapping(value = APP_ROOT+ "/entreprises/delete/{idEntreprise}")
    void  deleteById(@Param("idEntreprise") Long id);
}
