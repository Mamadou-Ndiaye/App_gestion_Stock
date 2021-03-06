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
import sn.ucad.gestionstock.repository.LigneVenteRepository;
import sn.ucad.gestionstock.repository.VenteRepository;
import sn.ucad.gestionstock.services.MvtStkService;
import sn.ucad.gestionstock.services.VenteService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private VenteRepository venteRepository;
    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;
    private MvtStkService mvtStkService;

    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository, ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository, MvtStkService mvtStkService) {
        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.mvtStkService = mvtStkService;
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
                articleErrors.add("Aucun article avec ID {} "+ ligneVenteDto.getArticleDto().getIdArticle() + "n'a ??t?? trouv?? dans la base de donn??es ");
            }
        });

        if (!articleErrors.isEmpty())
        {
            log.error("L Objet vente n est pas ??t?? trouv?? dans la base de donn??es",articleErrors);
            throw  new InvalidEntityException("Un ou plusieurs aricle n'pas ??t?? trouv?? dans la BD ",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }

        Vente savedVente = venteRepository.save(VenteDto.toEntity(venteDto));

        venteDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
            updateMvtStk(ligneVente);
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
                .orElseThrow(() -> new EntityNotFoundException("Aucun Vente avec id "+ id +" n 'a pas ??t?? trouv??  dans la BD",ErrorCodes.VENTE_NOT_FOUND) );
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
                .orElseThrow(() -> new EntityNotFoundException("Aucun Vente avec code {} "+ code +" n 'a pas ??t?? trouv??  dans la BD",ErrorCodes.VENTE_NOT_FOUND) );
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            log.error("Vente is NULL");
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
       if(!ligneVentes.isEmpty())
       {
           log.error("Impossible de supprimer une vente contenant des lignes de ventes");
           throw  new InvalidOperationException("Impossible de supprimer une vente, qui a deja des lignes de ventes", ErrorCodes.VENTE_ALREADY_IN_USE);
       }
        venteRepository.deleteById(id);
    }

    private  void  updateMvtStk(LigneVente ligneVente)
    {

            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .articleDto(ArticleDto.fromEntity(ligneVente.getArticle()))
                    .dateMvt(new Date())
                    .typeMvt(TypeMvtStk.SORTIE)
                    .sourceMvt(SourceMvtStk.VENTE)
                    .quantite(ligneVente.getQuantite())
                    .idEntreprise(ligneVente.getIdEntreprise())
                    .build();
            mvtStkService.sortieStock(mvtStkDto);

    }
}
