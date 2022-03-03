package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.gestionstock.dto.FournisseurDto;
import sn.ucad.gestionstock.dto.UtilisateurDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.model.Fournisseur;
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

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        List<String>  errors = UtilisateurValidator.validate(utilisateurDto);

        if (!errors.isEmpty())
        {
            log.error("Utilisateur is not valide  {}", utilisateurDto);
            throw  new InvalidEntityException("L'Utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID);

        }

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
        if ( mail == null)
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
}
