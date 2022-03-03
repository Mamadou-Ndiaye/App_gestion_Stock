package sn.ucad.gestionstock.controller.api;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.FournisseurDto;

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseurs/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}")
    FournisseurDto  findById(@PathVariable("idFournisseur") Long id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all")
    List<FournisseurDto> findAll();

    @GetMapping(value = APP_ROOT + "/fournisseurs/update/{idFournisseur}")
    FournisseurDto update(@PathVariable("id") Long id, @RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/byMail")
    FournisseurDto  findByMail(@Param("mail") String mail);

    @GetMapping(value = APP_ROOT + "/fournisseurs/byPrenom")
    List<FournisseurDto>  findByPrenom(@Param("prenom") String prenom);

    @DeleteMapping(value = APP_ROOT+"/fournisseurs/{idFournisseur}")
    void   deleteById(@PathVariable("idFournisseur") Long id);
}
