package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.CommandeFournisseurDto;
import sn.ucad.gestionstock.dto.LigneCommandeFournisseurDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.model.*;

import sn.ucad.gestionstock.repository.*;
import sn.ucad.gestionstock.services.CommandeFournisseurService;

import sn.ucad.gestionstock.validator.CommandeFournisseurValidator;

import java.util.ArrayList;
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


    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, ArticleRepository articleRepository, FournisseurRepository fournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.articleRepository = articleRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {

        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);


        if (!errors.isEmpty())
        {
            log.error("Commande Fournisseur n'est pas valide");
            throw  new InvalidEntityException("La commande fournisseur n est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
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
}
