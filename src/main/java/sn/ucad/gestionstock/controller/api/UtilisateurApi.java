package sn.ucad.gestionstock.controller.api;

import io.swagger.annotations.Api;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.ChangerMotDePasseUtilisateurDto;
import sn.ucad.gestionstock.dto.UtilisateurDto;

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;



@Api(APP_ROOT + "/utilisateurs")
public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateurs/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(UtilisateurDto utilisateurDto);

    @PostMapping(value = APP_ROOT + "/utilisateurs/addRoleToUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addRoleToUser(String username, String roleName);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}")
    UtilisateurDto  findById(@PathVariable("idUtilisateur") Long id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all")
    List<UtilisateurDto> findAll();

    @PutMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}")
    UtilisateurDto update(@PathVariable("idUtilisateur") Long id, @RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/byMail")
    UtilisateurDto  findByMail(@Param("mail") String mail);

    @GetMapping(value = APP_ROOT + "/utilisateurs/byPrenom")
    List<UtilisateurDto>  findByPrenom(@Param("prenom") String prenom);

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}")
    void   deleteById(@PathVariable("idUtilisateur") Long id);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/changepassword")
    UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto);

}
