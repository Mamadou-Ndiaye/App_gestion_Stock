package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.CommandeClientDto;
import sn.ucad.gestionstock.dto.LigneCommandeClientDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.model.Client;
import sn.ucad.gestionstock.model.CommandeClient;
import sn.ucad.gestionstock.model.LigneCommandeClient;
import sn.ucad.gestionstock.repository.ArticleRepository;
import sn.ucad.gestionstock.repository.ClientRepository;
import sn.ucad.gestionstock.repository.CommandeClientRepository;
import sn.ucad.gestionstock.repository.LigneCommandeClientRepository;
import sn.ucad.gestionstock.services.CommandeClientService;
import sn.ucad.gestionstock.validator.CommandeClientValidator;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ArticleRepository articleRepository;
    private ClientRepository clientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ArticleRepository articleRepository, ClientRepository clientRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);

        if (!errors.isEmpty())
        {
            log.error("Commande Client n'est pas valide");
            throw  new InvalidEntityException("La commande client n est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClientDto().getIdClient());
        if (! client.isPresent())
        {
            log.warn("Client with ID {} was not found in the BD",commandeClientDto.getClientDto().getIdClient());
            throw  new EntityNotFoundException(" Aucun client avec ID {} " + commandeClientDto.getClientDto().getIdClient() + " n a été trouvé dans la base de données",ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeClientDto.getLigneCommandeClientDtos() != null)
        {
            commandeClientDto.getLigneCommandeClientDtos().forEach( ligneCommandeClientDto -> {
                if (ligneCommandeClientDto.getArticleDto() != null) {
                    Optional<Article> article = articleRepository.findById(ligneCommandeClientDto.getArticleDto().getIdArticle());
                    if (!article.isPresent())
                    {
                         articleErrors.add("L'article avec ID "+ligneCommandeClientDto.getArticleDto().getIdArticle() + " n'existe pas");
                    }
                   /* else
                    {
                        articleErrors.add("Impossible d enregistrer une commande avec un article NULL ");
                    }*/
                }
            });
        }

        if (!articleErrors.isEmpty())
        {
            log.warn("");
            throw  new  InvalidEntityException("L'article n'existe pas dans la base de données ",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }

        CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));


        if (commandeClientDto.getLigneCommandeClientDtos() != null) {
            commandeClientDto.getLigneCommandeClientDtos().forEach(ligCmdeCltDto -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdeCltDto);
                ligneCommandeClient.setCommandeClient(saveCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }


        return CommandeClientDto.fromEntity(saveCmdClt);
    }

    @Override
    public CommandeClientDto findById(Long id) {
        if (id == null) {
            log.error("Commande cLient is NULL");
            return null;
        }

        return  commandeClientRepository.findById(id).map(CommandeClientDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("Aucun Commande Client avec id "+ id +" n 'a pas été trouvé  dans la BD",ErrorCodes.COMMANDE_CLIENT_NOT_FOUND) );
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Commande cLient is NULL");
            return null;
        }
        return  commandeClientRepository.findByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun Commande Client avec code "+ code +" n 'a pas été trouvé  dans la BD",ErrorCodes.COMMANDE_CLIENT_NOT_FOUND) );

    }



    @Override
    public List<CommandeClientDto> findAll() {
        return  commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("Commande cLient is NULL");
        }
        commandeClientRepository.deleteById(id);
    }
}
