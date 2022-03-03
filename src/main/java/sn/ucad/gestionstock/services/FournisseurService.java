package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.EntrepriseDto;
import sn.ucad.gestionstock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto  findById(Long id);

    List<FournisseurDto> findAll();

    FournisseurDto update(Long id, FournisseurDto fournisseurDto);

    FournisseurDto  findByMail(String mail);

    List<FournisseurDto>  findByPrenom(String prenom);

    void   deleteById(Long id);
}
