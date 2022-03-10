package sn.ucad.gestionstock.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.CommandeClientDto;

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

public interface CommandeClientApi {

    @PostMapping(value =APP_ROOT + "/commandeclients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(value =  APP_ROOT+ "/commandeclients/{idCmdClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto  findById(@PathVariable("idCmdClient") Long id);

    @GetMapping(value =  APP_ROOT+ "/commandeclients/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientDto> findAll();

    @GetMapping(value = APP_ROOT + "/commandeclients/{codeCmdClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findByCode(@PathVariable("codeCmdClient") String code);

    @DeleteMapping(value = APP_ROOT + "/commandeclients/delete/{idCmdClient}")
    void   deleteById(@PathVariable("idCmdClient")  Long id);
}
