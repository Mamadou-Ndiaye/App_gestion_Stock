package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.gestionstock.dto.ClientDto;
import sn.ucad.gestionstock.dto.EntrepriseDto;
import sn.ucad.gestionstock.dto.FournisseurDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.model.Entreprise;
import sn.ucad.gestionstock.model.Fournisseur;
import sn.ucad.gestionstock.repository.FournisseurRepository;
import sn.ucad.gestionstock.services.FournisseurService;
import sn.ucad.gestionstock.validator.FournisseurValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Slf4j
@Service
public class FounisseurSerrviceImpl implements FournisseurService {

    FournisseurRepository fournisseurRepository;

    public FounisseurSerrviceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }


    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String>  errors = FournisseurValidator.validate(fournisseurDto);

        if (!errors.isEmpty())
        {
            log.error("Fournisseur is not valide  {}", fournisseurDto);
            throw  new InvalidEntityException("Le Fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID);

        }

        return  FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto)));
    }



    @Override
    public FournisseurDto findById(Long id) {
        if (id == null)
        {
            log.error("Fournisseur is NULL");
            return  null;
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);


        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(()-> new EntityNotFoundException("Aucun fournisseur avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public FournisseurDto update(Long id, FournisseurDto fournisseurDto) {
        if (id == null)
        {
            log.error("Fournisseur is NULL");
            return  null;
        }
        Fournisseur fournisseur = fournisseurRepository.findById(id).get();
        fournisseur.setNom(fournisseur.getNom());
        fournisseur.setMail(fournisseur.getMail());
        fournisseur.setPrenom(fournisseur.getPrenom());


        return Optional.of(FournisseurDto.fromEntity(fournisseur)).orElseThrow(()-> new EntityNotFoundException("Aucune fournisseur avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public FournisseurDto findByMail(String mail) {
        if ( mail == null)
        {
            log.error("Fournisseur is NULL");
            return  null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findByMail(mail);

        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(()-> new EntityNotFoundException("Aucun Fournisseur avec mail = "+ mail + "n'a ete trouve dans la base de donnees",ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findByPrenom(String prenom) {
        if ( prenom == null)
        {
            log.error("Client is NULL");
            return  null;
        }
        List<Fournisseur> fournisseurs = fournisseurRepository.findByPrenom(prenom);

        return  fournisseurs.stream().map(FournisseurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (id== null)
        {
            log.error("Fournisseur is NULL");
            return;
        }

        fournisseurRepository.deleteById(id);
    }
}
