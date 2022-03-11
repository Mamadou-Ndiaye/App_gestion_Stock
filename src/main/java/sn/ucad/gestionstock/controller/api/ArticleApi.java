package sn.ucad.gestionstock.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.ArticleDto;

import java.util.List;
import static   sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

@Api(APP_ROOT + "/articles")
public interface ArticleApi {



   @ApiOperation(value = "Enregister  un article !", notes = "Cette methode permet d enregistrer ou modifier un article",response = ArticleDto.class)
   @ApiResponses(value = {@ApiResponse(code = 200, message = "L Objet article crée / modifié"), @ApiResponse(code = 404, message = "L Objet article n 'est pas valide")})
   @PostMapping(value = APP_ROOT + "/articles/create" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto save(@RequestBody  ArticleDto articleDto);

  @ApiOperation(value = "rechercher  un article !", notes = "Cette methode permet de rechercher un article dans la BDD avec son ID",response = ArticleDto.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Article a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun article n'a été trouvé dans la BDD avec l ID fournie")})
  @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  ArticleDto findById(@PathVariable("idArticle") Long id);


    @ApiOperation(value = "rechercher  un article avec un code !", notes = "Cette methode permet de rechercher un article dans la BDD avec son code",response = ArticleDto.class)
   @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Article a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun article n'a été trouvé dans la BDD avec le code fournie")})
    @GetMapping(value = APP_ROOT + "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);


    @ApiOperation(value = "La liste des articles  !", notes = "Cette methode permet de rechercher un article dans la BDD avec son ID",responseContainer ="List<ArticleDto>")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Article a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun article n'a été trouvé dans la BDD avec l ID fournie")})
    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @ApiOperation(value = "Supprimer un article !", notes = "Cette methode permet de rechercher un article dans la BDD avec son ID",response = ArticleDto.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "L'Article a été trouvé dans la base de données"),@ApiResponse(code = 404, message = "Aucun article n'a été trouvé dans la BDD avec l ID fournie")})
    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
    void  deleteById(@PathVariable("idArticle") Long id);
}
