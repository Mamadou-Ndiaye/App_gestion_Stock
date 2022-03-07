package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.CommandeFournisseurDto;
import sn.ucad.gestionstock.dto.LigneVenteDto;
import sn.ucad.gestionstock.dto.VenteDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.model.LigneVente;
import sn.ucad.gestionstock.model.Vente;
import sn.ucad.gestionstock.repository.ArticleRepository;
import sn.ucad.gestionstock.repository.LigneVenteRepository;
import sn.ucad.gestionstock.repository.VenteRepository;
import sn.ucad.gestionstock.services.VenteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private VenteRepository venteRepository;
    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository, ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {

        List<String>  errors = new ArrayList<>();

        if (!errors.isEmpty())
        {
            log.error(" Vente n'est pas valide ");
            throw  new InvalidEntityException("", ErrorCodes.VENTE_NOT_VALID,errors);

        }

        List<String> articleErrors = new ArrayList<>();

        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            Optional<Article>  article = articleRepository.findById(ligneVenteDto.getArticleDto().getIdArticle());
            if(!article.isPresent())
            {
                articleErrors.add("Aucun article avec ID {} "+ ligneVenteDto.getArticleDto().getIdArticle() + "n'a été trouvé dans la base de données ");
            }
        });

        if (!articleErrors.isEmpty())
        {
            log.error("L Objet vente n est pas été trouvé dans la base de données",articleErrors);
            throw  new InvalidEntityException("Un ou plusieurs aricle n'pas été trouvé dans la BD ",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }

        Vente savedVente = venteRepository.save(VenteDto.toEntity(venteDto));

        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);

        });


        return VenteDto.fromEntity(savedVente);
    }

    @Override
    public VenteDto findById(Long id) {
        if (id == null) {
            log.error("Vente is NULL");
            return null;
        }

        return  venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun Vente avec id "+ id +" n 'a pas été trouvé  dans la BD",ErrorCodes.VENTE_NOT_FOUND) );
    }

    @Override
    public List<VenteDto> findAll() {
        return  venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public VenteDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Vente is NULL");
            return null;
        }
        return  venteRepository.findByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun Vente avec code {} "+ code +" n 'a pas été trouvé  dans la BD",ErrorCodes.VENTE_NOT_FOUND) );
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("Vente is NULL");
        }
        venteRepository.deleteById(id);
    }
}
