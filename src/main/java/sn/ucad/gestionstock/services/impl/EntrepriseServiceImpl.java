package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sn.ucad.gestionstock.dto.EntrepriseDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;

import sn.ucad.gestionstock.model.Entreprise;
import sn.ucad.gestionstock.repository.EntrepriseRepository;
import sn.ucad.gestionstock.services.EntrepriseService;
import sn.ucad.gestionstock.validator.EntrepriseValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Slf4j
@Service
public class EntrepriseServiceImpl implements EntrepriseService {

    EntrepriseRepository  entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String>  errors = EntrepriseValidator.validate(entrepriseDto);

        if (!errors.isEmpty())
        {
            log.error("Entreprise is not valide  {}", entrepriseDto);
            throw  new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.CLIENT_NOT_VALID);

        }

        return  EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));
    }

    @Override
    public EntrepriseDto findById(Long id) {
        if (id== null)
        {
            log.error("Entreprise is NULL");
            return  null;
        }

        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);


        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(()-> new EntityNotFoundException("Aucune entreprise avec ID = "+ entreprise + "n'a ete trouve dans la base de donnees",ErrorCodes.ENTREPRISE_NOT_VALID));
    }

    @Override
    public EntrepriseDto update(Long id, EntrepriseDto entrepriseDto) {
        if (id == null)
        {
            log.error("Entreprise is NULL");
            return  null;
        }
        Entreprise entreprise = entrepriseRepository.findById(id).get();
        entreprise.setNom(entreprise.getNom());
        entreprise.setMail(entreprise.getMail());
        entreprise.setSiteWeb(entreprise.getSiteWeb());

        return Optional.of(EntrepriseDto.fromEntity(entreprise)).orElseThrow(()-> new EntityNotFoundException("Aucune entreprise avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EntrepriseDto findByNom(String nom) {
        if ( nom == null)
        {
            log.error("Entreprise is NULL");
            return  null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findByNom(nom);

        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(()-> new EntityNotFoundException("Aucune Entreprise avec mail = "+ nom + "n'a ete trouve dans la base de donnees",ErrorCodes.ENTREPRISE_NOT_FOUND));

    }

    @Override
    public void deleteById(Long id) {
        if (id== null)
        {
            log.error("Entreprise is NULL");
            return;
        }

        entrepriseRepository.deleteById(id);

    }
}
