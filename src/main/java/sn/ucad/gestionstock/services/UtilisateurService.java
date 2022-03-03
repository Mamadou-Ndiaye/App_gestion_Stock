package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto  findById(Long id);

    List<UtilisateurDto> findAll();

    UtilisateurDto update(Long id, UtilisateurDto utilisateurDto);

    UtilisateurDto  findByMail(String mail);

    List<UtilisateurDto>  findByPrenom(String prenom);

    void   deleteById(Long id);
}
