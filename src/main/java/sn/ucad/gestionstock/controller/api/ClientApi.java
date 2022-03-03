package sn.ucad.gestionstock.controller.api;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.ClientDto;

import java.util.List;

import  static  sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

public interface ClientApi {

    @PostMapping(value =APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value =  APP_ROOT+ "/clients/{idClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto  findById(@PathVariable("idClient") Long id);

    @GetMapping(value =  APP_ROOT+ "/clients/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @GetMapping(value =  APP_ROOT+ "/clients/byMAil" , produces = MediaType.APPLICATION_JSON_VALUE)
    ClientDto  findByMail(@Param("mail") String mail);

    @GetMapping(value =  APP_ROOT+ "/clients/byPrenom" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto>  findByPrenom(@Param("prenom") String prenom);

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    void   deleteById(@PathVariable("idClient") Long id);
}
