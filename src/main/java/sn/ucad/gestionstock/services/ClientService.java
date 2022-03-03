package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto   save(ClientDto clientDto);

    ClientDto  findById(Long id);

    List<ClientDto>  findAll();

    ClientDto  findByMail(String mail);

    List<ClientDto>  findByPrenom(String prenom);

    void   deleteById(Long id);
}
