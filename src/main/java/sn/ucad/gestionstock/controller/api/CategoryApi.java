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

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;


@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @ApiOperation(value = "Enregister  une category !", notes = "Cette methode permet d enregistrer ou modifier une Categorie",response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L Objet category crée / modifié"), @ApiResponse(code = 404, message = "L Objet category n 'est pas valide")})
    @PostMapping(value = APP_ROOT + "/categories/create" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @ApiOperation(value = "rechercher  une categorie !", notes = "Cette methode permet de rechercher une categorie dans la BDD avec son ID",response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La Categorie a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun categorie n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById( @PathVariable("idCategory") Long id);


    @ApiOperation(value = "La liste des articles  !", notes = "Cette methode permet de renvoyer la liste des catégories de la BDD",responseContainer ="List<CategoryDto>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La liste des categories"),@ApiResponse(code = 404, message = "Aucun categorie n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @PutMapping(value =  APP_ROOT+ "/categories/update/{idCategory}")
    CategoryDto update(@PathVariable("idCategory") Long id, @RequestBody CategoryDto categoryDto);


    @ApiOperation(value = "rechercher  une categorie avec un code !", notes = "Cette methode permet de rechercher une categorie dans la BDD avec son code",response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La categorie a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun categorie n'a été trouvé dans la BDD avec le code fournie")})
    @GetMapping(value = APP_ROOT + "/categories/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode(@PathVariable("code") String code);

    @ApiOperation(value = "rechercher  une categorie par designation !", notes = "Cette methode permet de rechercher une categorie dans la BDD par designation",response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "La categorie a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun categorie n'a été trouvé dans la BDD par la designation fournie")})
    @GetMapping(value = APP_ROOT + "/categories/byDesignation", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findByDesignation(@Param("designation") String designation);

    @ApiOperation(value = "Supprimer une categorie !", notes = "Cette methode permet de rechercher une categorie dans la BDD par désignation",response = CategoryDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Article a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun catégorie n'a été trouvé dans la BDD par la désignation fournie")})
    @DeleteMapping(value =  APP_ROOT + "/categories/delete/{idCategory}")
    void  deleteById(@PathVariable("idCategory") Long id);
}
