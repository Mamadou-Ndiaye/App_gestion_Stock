package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.*;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.exception.InvalidOperationException;
import sn.ucad.gestionstock.model.*;
import sn.ucad.gestionstock.repository.ArticleRepository;
import sn.ucad.gestionstock.repository.ClientRepository;
import sn.ucad.gestionstock.repository.CommandeClientRepository;
import sn.ucad.gestionstock.repository.LigneCommandeClientRepository;
import sn.ucad.gestionstock.services.CommandeClientService;
import sn.ucad.gestionstock.services.MvtStkService;
import sn.ucad.gestionstock.validator.ArticleValidator;
import sn.ucad.gestionstock.validator.CommandeClientValidator;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    private MvtStkService mvtStkService;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ArticleRepository articleRepository, ClientRepository clientRepository, LigneCommandeClientRepository ligneCommandeClientRepository, MvtStkService mvtStkService) {
        this.commandeClientRepository = commandeClientRepository;
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);

        if (!errors.isEmpty())
        {
            log.error("Commande Client n'est pas valide");
            throw  new InvalidEntityException("La commande client n est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }

        if (commandeClientDto.getIdCommandeClient() != null && commandeClientDto.isCommandeClientLivree())
        {
            throw  new InvalidOperationException("Impossible de modifier la commande client lorsqu elle est livrée", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);

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

        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(id);

        if (!ligneCommandeClients.isEmpty())
        {
            log.error("Impossible de supprimer cette commande client car, il contient des lignes de commande client");
            throw  new InvalidOperationException("Impossible de supprimer ce client car, il est deja utilise dansdes lignes de commandes clients", ErrorCodes.COMMANDE_CLIENT_ALREADY_IN_USE);
        }

        commandeClientRepository.deleteById(id);
    }

    @Override
    public CommandeClientDto updateEtatCommandeClient(Long id, EtatCommande etatCommande) {
       checkIdCommande(id);
        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("Etat Commande cLient is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande client avec un etat commande null", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        CommandeClientDto commandeClientDto =  checkEtatCommande(id);
        commandeClientDto.setEtatCommande(etatCommande);

        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
       if (commandeClientDto.isCommandeClientLivree()) {
           updateMvtStk(id);
       }

        return CommandeClientDto.fromEntity(savedCmdClt);

    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite) {
        /*if (idCommande == null) {
            log.error("Commande cLient is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande client avec un ID NULL", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);

        }*/
        checkIdCommande(idCommande);
        /*if (idLigneCommande == null) {
            log.error("Id Ligne commande Cient is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande client avec un etat commande null", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }*/
        checkIdLigneCommande(idLigneCommande );

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO)==0) {
            log.error("Quantite  NULL ou egal a ZERO");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande client avec une quantite null ou egala ZERO", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        CommandeClientDto commandeClientDto = checkEtatCommande(idCommande);

        Optional<LigneCommandeClient> ligneCommandeClientOptional = findLigneCommandeClient(idLigneCommande);

        LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptional.get();
        ligneCommandeClient.setQuantite(quantite);
        ligneCommandeClientRepository.save(ligneCommandeClient);

        return  commandeClientDto;
    }



    @Override
    public CommandeClientDto updateClient(Long idCommande, Long idClient) {
       checkIdCommande(idCommande);
        if (idClient == null) {
            log.error("Id Client is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande client avec unId client null", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }

        CommandeClientDto  commandeClientDto = checkEtatCommande(idCommande);

        Optional<Client> clientOptional = clientRepository.findById(idClient);
        if (!clientOptional.isPresent())
        {
            throw  new EntityNotFoundException("Aucun Client avec idcLient "+ idClient +" n 'a pas été trouvé  dans la BD",ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);

        }

        commandeClientDto.setClientDto(ClientDto.fromEntity(clientOptional.get()));

        return  CommandeClientDto.fromEntity(
                commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto))
        );
    }



    @Override
    public CommandeClientDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle) {
       checkIdCommande(idCommande);
       checkIdLigneCommande(idLigneCommande);
       checkIdArticle(newIdArticle, "nouvel");

       CommandeClientDto  commandeClientDto = checkEtatCommande(idCommande);

       Optional<LigneCommandeClient> ligneCommandeClient =findLigneCommandeClient(idLigneCommande);

       Optional<Article> articleOptional = articleRepository.findById(newIdArticle);
       if (!articleOptional.isPresent())
       {
           throw  new EntityNotFoundException("Aucun Article avec id "+ newIdArticle +" n 'a pas été trouvé  dans la BD",ErrorCodes.ARTICLE_NOT_FOUND);

       }


       List<String> errors =   ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
        if (!errors.isEmpty())
        {
            throw  new InvalidEntityException("Article not valid",ErrorCodes.ARTICLE_NOT_VALID,errors);
        }

        LigneCommandeClient ligneCommandeClientToSaved = ligneCommandeClient.get();

        ligneCommandeClientToSaved.setArticle(articleOptional.get());
        ligneCommandeClientRepository.save(ligneCommandeClientToSaved);


        return commandeClientDto;
    }

    @Override
    public List<LigneCommandeClientDto> findAllLignesCommandeClientByCommandeClientId(Long idCommande) {
        return ligneCommandeClientRepository.findAllByCommandeClientId(idCommande).stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public CommandeClientDto deleteArticle(Long idCommande, Long idLigneCommande) {

        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        CommandeClientDto  commandeClientDto = checkEtatCommande(idCommande);
        Optional<LigneCommandeClient> ligneCommandeClient =findLigneCommandeClient(idLigneCommande);
        ligneCommandeClientRepository.deleteById(idLigneCommande);


        return commandeClientDto;
    }

    private  void  checkIdCommande(Long idCommande)
    {
        if (idCommande == null) {
            log.error("Commande cLient is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande client avec un ID NULL", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);

        }
    }

    private  void  checkIdLigneCommande(Long idLigneCommande)
    {
        if (idLigneCommande == null) {
            log.error("Id Ligne Commande cLient is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande client avec un ID Ligne Commande NULL", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);

        }
    }

    private CommandeClientDto checkEtatCommande(Long idCommande) {

        CommandeClientDto commandeClientDto = findById(idCommande);
        if (commandeClientDto.isCommandeClientLivree())
        {
            throw  new InvalidOperationException("Impossible de modifier la commande client lorsqu elle est livrée", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
        }
        return  commandeClientDto;
    }

    private  void  checkIdArticle(Long idArticle, String msg)
    {
        if (idArticle == null) {
            log.error("Id Article is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande client avec "+ msg +" un ID Article NULL", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);

        }
    }

    private Optional<LigneCommandeClient> findLigneCommandeClient(Long idLigneCommande) {
        Optional<LigneCommandeClient> ligneCommandeClientOptional =  ligneCommandeClientRepository.findById(idLigneCommande);

        if (!ligneCommandeClientOptional.isPresent())
        {
            throw  new EntityNotFoundException("Aucun Ligne de Commande Client avec iddLigneCommande "+ idLigneCommande +" n 'a pas été trouvé  dans la BD",ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);

        }
        return ligneCommandeClientOptional;

    }

    private  void  updateMvtStk(Long idCommande)
    {
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);

        ligneCommandeClients.forEach(ligneCommandeClient -> {
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .articleDto(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                    .dateMvt(new Date())
                    .typeMvt(TypeMvtStk.SORTIE)
                    .sourceMvt(SourceMvtStk.COMMANDE_CLIENT)
                    .quantite(ligneCommandeClient.getQuantite())
                    .idEntreprise(ligneCommandeClient.getIdEntreprise())
                    .build();
            mvtStkService.sortieStock(mvtStkDto);
        });

    }


}
