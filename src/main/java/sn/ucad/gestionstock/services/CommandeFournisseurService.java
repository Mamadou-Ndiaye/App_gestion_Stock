package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    CommandeFournisseurDto  findById(Long id);

    List<CommandeFournisseurDto> findAll();

    CommandeFournisseurDto findByCode(String code);

    void   deleteById(Long id);
}
