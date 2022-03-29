package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.ChangerMotDePasseUtilisateurDto;
import sn.ucad.gestionstock.dto.FournisseurDto;
import sn.ucad.gestionstock.dto.UtilisateurDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.exception.InvalidOperationException;
import sn.ucad.gestionstock.model.Fournisseur;
import sn.ucad.gestionstock.model.Roles;
import sn.ucad.gestionstock.model.Utilisateur;
import sn.ucad.gestionstock.repository.UtilisateurRepository;
import sn.ucad.gestionstock.services.UtilisateurService;
import sn.ucad.gestionstock.validator.FournisseurValidator;
import sn.ucad.gestionstock.validator.UtilisateurValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Slf4j
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    UtilisateurRepository utilisateurRepository;


    private BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,BCryptPasswordEncoder bCryptEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.bCryptEncoder = bCryptEncoder;
    }


    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        List<String>  errors = UtilisateurValidator.validate(utilisateurDto);

        if (!errors.isEmpty())
        {
            log.error("Utilisateur is not valide  {}", utilisateurDto);
            throw  new InvalidEntityException("L'Utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID);

        }
        if (utilisateurRepository.findByMail(utilisateurDto.getMail()).isPresent()) {
            log.warn("User already exists");
            throw new RuntimeException("User already exists");
        }
        utilisateurDto.setMotDePasse(bCryptEncoder.encode(utilisateurDto.getMotDePasse()));
        log.info(" ******** Mot de Passe *******************{}  " + utilisateurDto.getMotDePasse());
        log.info(" ******** Mot de Mail *******************{}  " + utilisateurDto.getMail());
        utilisateurDto.getRolesDtos().forEach(rolesDto -> log.info("****** Role utilisateur",rolesDto.getRoleName()));

        return  UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null)
        {
            log.error("Fournisseur is NULL");
            return  null;
        }

        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);


        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(()-> new EntityNotFoundException("Aucun utilisateur avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto update(Long id, UtilisateurDto utilisateurDto) {
        if (id == null)
        {
            log.error("Utilisateur is NULL");
            return  null;
        }
        Utilisateur utilisateur = utilisateurRepository.findById(id).get();
        utilisateur.setNom(utilisateur.getNom());
        utilisateur.setMail(utilisateur.getMail());
        utilisateur.setPrenom(utilisateur.getPrenom());


        return Optional.of(UtilisateurDto.fromEntity(utilisateur)).orElseThrow(()-> new EntityNotFoundException("Aucune Utilisateur avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public UtilisateurDto findByMail(String mail) {
        if ( !StringUtils.hasLength(mail))
        {
            log.error("Utilisateur is NULL");
            return  null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByMail(mail);

        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(()-> new EntityNotFoundException("Aucun Utilisateur avec mail = "+ mail + "n'a ete trouve dans la base de donnees",ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findByPrenom(String prenom) {
        if ( prenom == null)
        {
            log.error("Utilisateur is NULL");
            return  null;
        }
        List<Utilisateur> utilisateurs = utilisateurRepository.findByPrenom(prenom);

        return  utilisateurs.stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
        {
            log.error("Utilisateur is NULL");
            return;
        }

        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto) {
        validate(dto);
     Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(dto.getId());
     if (!utilisateurOptional.isPresent())
     {
         log.warn("Utilisateur not found");
         throw  new EntityNotFoundException("Aucun utilisateur n a ete trouve avec cette ID"+dto.getId() ,ErrorCodes.UTILISATEUR_NOT_FOUND);
     }
     Utilisateur utilisateur = utilisateurOptional.get();
     utilisateur.setMotDePasse(dto.getMotDePasse());
        return  UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }

    private  void  validate(ChangerMotDePasseUtilisateurDto dto)
    {
        if (dto == null)
        {
            log.warn("Impossible de modifier le mot de passe avec un objet null");
            throw  new InvalidOperationException("Aucune information n aété fournie pour pouvoir changer le mdp", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

        if (dto.getId() == null)
        {
            log.warn("Impossible de modifier le mot de passe avec un ID null");
            throw  new InvalidOperationException("ID utilisateur NULL:: impossible de modifier le mot de passe d un user avec ID null", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);

        }

        if (!StringUtils.hasLength(dto.getMotDePasse()) || !StringUtils.hasLength(dto.getConfirmMotDePasse()))
        {
            log.warn("Impossible de modifier le mot de passe avec mot de passe null");
            throw  new InvalidOperationException("ID utilisateur NULL:: impossible de modifier le mot de passe d un user avec ID null", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);

        }

        if (!dto.getMotDePasse().equals(dto.getConfirmMotDePasse()))
        {
            log.warn("Impossible de modifier le mot de passe avec mot de passe avec eux 2 mot de passe differentes");
            throw  new InvalidOperationException("ID utilisateur NULL:: impossible de modifier le mot de passe d un user avec 2 mots de passe non conforme ", ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);

        }

    }
}
