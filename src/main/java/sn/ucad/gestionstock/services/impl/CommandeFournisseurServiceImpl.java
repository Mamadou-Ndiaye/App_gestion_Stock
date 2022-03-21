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

import sn.ucad.gestionstock.repository.*;
import sn.ucad.gestionstock.services.CommandeFournisseurService;

import sn.ucad.gestionstock.services.MvtStkService;
import sn.ucad.gestionstock.validator.ArticleValidator;
import sn.ucad.gestionstock.validator.CommandeFournisseurValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private ArticleRepository articleRepository;
    private FournisseurRepository fournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private MvtStkService mvtStkService;


    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, ArticleRepository articleRepository, FournisseurRepository fournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, MvtStkService mvtStkService) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.articleRepository = articleRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {

        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);


        if (!errors.isEmpty())
        {
            log.error("Commande Fournisseur n'est pas valide");
            throw  new InvalidEntityException("La commande fournisseur n est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
        }

        if (commandeFournisseurDto.getIdCommandeFournisseur() != null && commandeFournisseurDto.isCommandeClientLivree())
        {
            throw  new InvalidOperationException("Impossible de modifier la commande fournisseur lorsqu elle est livrée", ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);

        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseurDto().getIdFournisseur());
        if (! fournisseur.isPresent())
        {
            log.warn("Fournisseur with ID {} was not found in the BD",commandeFournisseurDto.getFournisseurDto().getIdFournisseur());
            throw  new EntityNotFoundException(" Aucun fournisseur avec ID {} " + commandeFournisseurDto.getFournisseurDto().getIdFournisseur()+ " n a été trouvé dans la base de données",ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeFournisseurDto.getLigneCommandeFournisseurDtos() != null)
        {
            commandeFournisseurDto.getLigneCommandeFournisseurDtos().forEach( ligneCommandeFournisseurDto -> {
                if (ligneCommandeFournisseurDto.getArticleDto() != null) {
                    Optional<Article> article = articleRepository.findById(ligneCommandeFournisseurDto.getArticleDto().getIdArticle());
                    if (!article.isPresent())
                    {
                        articleErrors.add("L'article avec ID "+ligneCommandeFournisseurDto.getArticleDto().getIdArticle() + " n'existe pas");
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

        CommandeFournisseur saveCmdFour = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));


        if (commandeFournisseurDto.getLigneCommandeFournisseurDtos() != null) {
            commandeFournisseurDto.getLigneCommandeFournisseurDtos().forEach(ligCmdeFourDto -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdeFourDto);
                ligneCommandeFournisseur.setCommandeFournisseur(saveCmdFour);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }


        return CommandeFournisseurDto.fromEntity(saveCmdFour);
    }

    @Override
    public CommandeFournisseurDto findById(Long id) {
        if (id == null) {
            log.error("Commande fournisseur is NULL");
            return null;
        }

        return  commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun Commande Fournisseur avec id "+ id +" n 'a pas été trouvé  dans la BD",ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND) );
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return  commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Commande fournisseur is NULL");
            return null;
        }
        return  commandeFournisseurRepository.findByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun Commande Fournisseur avec code {} "+ code +" n 'a pas été trouvé  dans la BD",ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND) );

    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("Commande fournissseur is NULL");
        }
        commandeFournisseurRepository.deleteById(id);
    }


    @Override
    public CommandeFournisseurDto updateEtatCommandeClient(Long id, EtatCommande etatCommande) {
        checkIdCommande(id);
        if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
            log.error("Etat Commande fournisseur is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande fournisseur avec un etat commande null", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }

        CommandeFournisseurDto commandeFournisseurDto =  checkEtatCommande(id);
        commandeFournisseurDto.setEtatCommande(etatCommande);

        CommandeFournisseur savedCmdClt = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));
        if (commandeFournisseurDto.isCommandeClientLivree()) {
            updateMvtStk(id);
        }

        return CommandeFournisseurDto.fromEntity(savedCmdClt);
    }




    @Override
    public CommandeFournisseurDto updateQuantiteCommande(Long idCommande, Long idLigneCommande, BigDecimal quantite) {
        checkIdLigneCommande(idLigneCommande );

        if (quantite == null || quantite.compareTo(BigDecimal.ZERO)==0) {
            log.error("Quantite  NULL ou egal a ZERO");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande fournisseur avec une quantite null ou egala ZERO", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }

        CommandeFournisseurDto commandeFournisseurDto = checkEtatCommande(idCommande);

        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

        LigneCommandeFournisseur ligneCommandeFournisseur = ligneCommandeFournisseurOptional.get();
        ligneCommandeFournisseur.setQuantite(quantite);
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

        return  commandeFournisseurDto;
    }


    @Override
    public CommandeFournisseurDto updateFournisseur(Long idCommande, Long idFournisseur) {
        checkIdCommande(idCommande);
        if (idFournisseur == null) {
            log.error("Id Fournisseur is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande du fournisseur avec un Id fournisseur null", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }

        CommandeFournisseurDto  commandeFournisseurDto = checkEtatCommande(idCommande);

        Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
        if (!fournisseurOptional.isPresent())
        {
            throw  new EntityNotFoundException("Aucun Fournisseur avec idFournisseur "+ idFournisseur +" n 'a pas été trouvé  dans la BD",ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);

        }

        commandeFournisseurDto.setFournisseurDto(FournisseurDto.fromEntity(fournisseurOptional.get()));

        return  CommandeFournisseurDto.fromEntity(
                commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto))
        );
    }

    @Override
    public CommandeFournisseurDto updateArticle(Long idCommande, Long idLigneCommande, Long newIdArticle) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);
        checkIdArticle(newIdArticle, "nouvel");

        CommandeFournisseurDto  commandeFournisseurDto = checkEtatCommande(idCommande);

        Optional<LigneCommandeFournisseur> ligneCommandeFournisseur =findLigneCommandeFournisseur(idLigneCommande);

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

        LigneCommandeFournisseur ligneCommandeFournisseurToSaved = ligneCommandeFournisseur.get();

        ligneCommandeFournisseurToSaved.setArticle(articleOptional.get());
        ligneCommandeFournisseurRepository.save(ligneCommandeFournisseurToSaved);


        return commandeFournisseurDto;
    }


    @Override
    public CommandeFournisseurDto deleteArticle(Long idCommande, Long idLigneCommande) {
        checkIdCommande(idCommande);
        checkIdLigneCommande(idLigneCommande);

        CommandeFournisseurDto  commandeFournisseurDto = checkEtatCommande(idCommande);
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseur =findLigneCommandeFournisseur(idLigneCommande);
        ligneCommandeFournisseurRepository.deleteById(idLigneCommande);


        return commandeFournisseurDto;
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLignesCommandeFournisseurByCommandeFournisseurId(Long idCommande) {
        return ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande).stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    private  void  checkIdCommande(Long idCommande)
    {
        if (idCommande == null) {
            log.error("Commande fournisseur is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande du fournisseur avec un ID NULL", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);

        }
    }

    private  void  checkIdLigneCommande(Long idLigneCommande)
    {
        if (idLigneCommande == null) {
            log.error("Id Ligne Commande fournisseur is NULL");
            throw  new InvalidOperationException("Impossible de modifier l'etat de la commande fournisseur avec un ID Ligne Commande NULL", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);

        }
    }


    private CommandeFournisseurDto checkEtatCommande(Long idCommande) {

        CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);
        if (commandeFournisseurDto.isCommandeClientLivree())
        {
            throw  new InvalidOperationException("Impossible de modifier la commande fournisseur lorsqu elle est livrée", ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
        }
        return  commandeFournisseurDto;
    }

    private void checkIdArticle(Long newIdArticle, String nouvel) {
    }



    private Optional<LigneCommandeFournisseur> findLigneCommandeFournisseur(Long idLigneCommande) {
        Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional =  ligneCommandeFournisseurRepository.findById(idLigneCommande);

        if (!ligneCommandeFournisseurOptional.isPresent())
        {
            throw  new EntityNotFoundException("Aucun Ligne de Commande fournisseur avec iddLigneCommande "+ idLigneCommande +" n 'a pas été trouvé  dans la BD",ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);

        }
        return ligneCommandeFournisseurOptional;

    }


    private  void  updateMvtStk(Long idCommande)
    {
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande);

        ligneCommandeFournisseurs.forEach(ligneCommandeFournisseur -> {
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .articleDto(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                    .dateMvt(new Date())
                    .typeMvt(TypeMvtStk.SORTIE)
                    .sourceMvt(SourceMvtStk.COMMANDE_CLIENT)
                    .quantite(ligneCommandeFournisseur.getQuantite())
                    .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                    .build();
            mvtStkService.sortieStock(mvtStkDto);
        });

    }

}
