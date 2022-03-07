package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);

    CommandeClientDto  findById(Long id);

    List<CommandeClientDto> findAll();

   CommandeClientDto findByCode(String code);

    void   deleteById(Long id);
}
