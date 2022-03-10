package sn.ucad.gestionstock.controller.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.CommandeFournisseurDto;

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

public interface CommandeFournisseurApi {

    @PostMapping(value =APP_ROOT + "/commandefournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto save(@RequestBody  CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value =  APP_ROOT+ "/commandefournisseurs/{idCmdFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto  findById(@PathVariable("idCmdFournisseur") Long id);

    @GetMapping(value =  APP_ROOT+ "/commandefournisseurs/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeFournisseurDto> findAll();

    @GetMapping(value = APP_ROOT + "/commandefournisseurs/{codeCmdFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findByCode(@PathVariable("codeCmdFournisseur") String code);

    @DeleteMapping(value = APP_ROOT + "/commandefournisseurs/delete/{idCmdFournisseur}")
    void   deleteById(@PathVariable("idCmdFournisseur") Long id);
}
