package sn.ucad.gestionstock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.FournisseurApi;
import sn.ucad.gestionstock.dto.FournisseurDto;
import sn.ucad.gestionstock.services.FournisseurService;

import java.util.List;

@RestController
public class FournisseurController  implements FournisseurApi {

    FournisseurService  fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        return  fournisseurService.save(fournisseurDto);
    }

    @Override
    public FournisseurDto findById(Long id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public FournisseurDto update(Long id, FournisseurDto fournisseurDto) {
        return fournisseurService.update(id,fournisseurDto);
    }

    @Override
    public FournisseurDto findByMail(String mail) {
        return fournisseurService.findByMail(mail);
    }

    @Override
    public List<FournisseurDto> findByPrenom(String prenom) {
        return  fournisseurService.findByPrenom(prenom);
    }

    @Override
    public void deleteById(Long id) {
           fournisseurService.deleteById(id);
    }
}
