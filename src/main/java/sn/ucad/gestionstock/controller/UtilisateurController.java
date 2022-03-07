package sn.ucad.gestionstock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.UtilisateurApi;
import sn.ucad.gestionstock.dto.UtilisateurDto;
import sn.ucad.gestionstock.services.UtilisateurService;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    UtilisateurService utilisateurService;


    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return  utilisateurService.save(utilisateurDto);
    }

    @Override
    public UtilisateurDto findById(Long id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public UtilisateurDto update(Long id, UtilisateurDto utilisateurDto) {
        return  utilisateurService.update(id,utilisateurDto);
    }

    @Override
    public UtilisateurDto findByMail(String mail) {
        return utilisateurService.findByMail(mail);
    }

    @Override
    public List<UtilisateurDto> findByPrenom(String prenom) {
        return  utilisateurService.findByPrenom(prenom);
    }

    @Override
    public void deleteById(Long id) {
           utilisateurService.deleteById(id);
    }
}
