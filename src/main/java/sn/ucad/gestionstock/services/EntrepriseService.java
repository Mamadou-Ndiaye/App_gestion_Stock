package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto entrepriseDto);

    EntrepriseDto findById(Long id);

    EntrepriseDto update(Long id, EntrepriseDto entrepriseDto);

    List<EntrepriseDto> findAll();

    EntrepriseDto findByNom(String nom);

   // List<EntrepriseDto> findByDesignation(String designation);

    void  deleteById(Long id);
}
