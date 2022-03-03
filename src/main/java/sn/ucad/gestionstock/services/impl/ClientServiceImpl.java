package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.ucad.gestionstock.dto.ClientDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;

import sn.ucad.gestionstock.model.Client;
import sn.ucad.gestionstock.repository.ClientRepository;
import sn.ucad.gestionstock.services.ClientService;
import sn.ucad.gestionstock.validator.ClientValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Slf4j
@Service
public class ClientServiceImpl  implements ClientService {

    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String>  errors = ClientValidator.validate(clientDto);

        if (!errors.isEmpty())
        {
            log.error("Client is not valide  {}", clientDto);
            throw  new InvalidEntityException("Le Client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID);

        }

        return  ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Long id) {
        if (id== null)
        {
            log.error("Client is NULL");
            return  null;
        }

        Optional<Client> client = clientRepository.findById(id);


        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(()-> new EntityNotFoundException("Aucun client avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto findByMail(String mail) {
        if ( mail == null)
        {
            log.error("Client is NULL");
            return  null;
        }
        Optional<Client> client = clientRepository.findByMail(mail);

        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(()-> new EntityNotFoundException("Aucun Client avec mail = "+ mail + "n'a ete trouve dans la base de donnees",ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findByPrenom(String prenom) {
        if ( prenom == null)
        {
            log.error("Client is NULL");
            return  null;
        }
        List<Client> clients = clientRepository.findByPrenom(prenom);

        return  clients.stream().map(ClientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id== null)
        {
            log.error("Client is NULL");
            return;
        }

        clientRepository.deleteById(id);
    }
}
