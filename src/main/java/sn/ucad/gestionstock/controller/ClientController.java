package sn.ucad.gestionstock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.ClientApi;
import sn.ucad.gestionstock.dto.ClientDto;
import sn.ucad.gestionstock.services.ClientService;

import java.util.List;

@RestController
public class ClientController  implements ClientApi {

    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        return  clientService.save(clientDto);
    }

    @Override
    public ClientDto findById(Long id) {
        return  clientService.findById(id);
    }

    @Override
    public List<ClientDto> findAll() {
        return  clientService.findAll();
    }

    @Override
    public ClientDto findByMail(String mail) {
        return  clientService.findByMail(mail);
    }

    @Override
    public List<ClientDto> findByPrenom(String prenom) {
        return  clientService.findByPrenom(prenom);
    }

    @Override
    public void deleteById(Long id) {
          clientService.deleteById(id);
    }
}
