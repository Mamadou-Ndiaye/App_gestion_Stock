package sn.ucad.gestionstock.controller.api;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.EntrepriseDto;

import java.util.List;
import  static  sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;


public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "/entreprises/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(EntrepriseDto entrepriseDto);

    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}")
    EntrepriseDto findById(Long id);

    @PutMapping(value = APP_ROOT+ "/entreprises/{idEntreprise}")
    EntrepriseDto update(@PathVariable("idEntreprise") Long id,@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = APP_ROOT+"/entreprise/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @GetMapping(value = APP_ROOT+"/entreprises/byNom")
    EntrepriseDto findByNom(@Param("nom") String nom);

    // List<EntrepriseDto> findByDesignation(String designation);

    @DeleteMapping(value = APP_ROOT+ "/entreprises/delete/{idEntreprise}")
    void  deleteById(@Param("idEntreprise") Long id);
}
