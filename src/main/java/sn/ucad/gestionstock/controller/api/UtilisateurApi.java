package sn.ucad.gestionstock.controller.api;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.UtilisateurDto;

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateurs/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}")
    UtilisateurDto  findById(@PathVariable("idUtilisateur") Long id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all")
    List<UtilisateurDto> findAll();

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}")
    UtilisateurDto update(@PathVariable("idUtilisateur") Long id, @RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/byMail")
    UtilisateurDto  findByMail(@Param("mail") String mail);

    @GetMapping(value = APP_ROOT + "/utilisateurs/byPrenom")
    List<UtilisateurDto>  findByPrenom(@Param("prenom") String prenom);

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}")
    void   deleteById(@PathVariable("idUtilisateur") Long id);
}
