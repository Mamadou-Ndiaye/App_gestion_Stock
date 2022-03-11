package sn.ucad.gestionstock.controller.api;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.VenteDto;

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;



@Api(APP_ROOT + "/ventes")
public interface VenteApi {

    @PostMapping(value =APP_ROOT + "/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    VenteDto save(@RequestBody  VenteDto venteDto);

    @GetMapping(value =  APP_ROOT+ "/ventes/{idVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    VenteDto  findById(@PathVariable("idVente") Long id);

    @GetMapping(value =  APP_ROOT+ "/ventes/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<VenteDto> findAll();

    @GetMapping(value = APP_ROOT + "/ventes/{codeVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    VenteDto findByCode(@PathVariable("codeVente") String code);

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{idVente}")
    void   deleteById(@PathVariable("idVente") Long id);
}
