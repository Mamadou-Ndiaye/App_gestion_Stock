package sn.ucad.gestionstock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.EntrepriseApi;
import sn.ucad.gestionstock.dto.EntrepriseDto;
import sn.ucad.gestionstock.services.EntrepriseService;

import java.util.List;

@RestController
public class EntrepriseController  implements EntrepriseApi {

    @Autowired
    EntrepriseService entrepriseService;

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        return entrepriseService.save(entrepriseDto);
    }

    @Override
    public EntrepriseDto findById(Long id) {
        return entrepriseService.findById(id);
    }

    @Override
    public EntrepriseDto update(Long id, EntrepriseDto entrepriseDto) {
        return entrepriseService.update(id,entrepriseDto);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return  entrepriseService.findAll();
    }

    @Override
    public EntrepriseDto findByNom(String nom) {
        return entrepriseService.findByNom(nom);
    }

    @Override
    public void deleteById(Long id) {
          entrepriseService.deleteById(id);
    }
}
