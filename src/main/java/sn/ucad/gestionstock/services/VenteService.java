package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.CommandeClientDto;
import sn.ucad.gestionstock.dto.VenteDto;

import java.util.List;

public interface VenteService {

    VenteDto save(VenteDto venteDto);

    VenteDto  findById(Long id);

    List<VenteDto> findAll();

   VenteDto findByCode(String code);

    void   deleteById(Long id);
}
