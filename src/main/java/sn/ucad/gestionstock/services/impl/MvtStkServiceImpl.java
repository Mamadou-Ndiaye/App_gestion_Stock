package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ucad.gestionstock.dto.MvtStkDto;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.model.TypeMvtStk;
import sn.ucad.gestionstock.repository.MvtStkRepository;
import sn.ucad.gestionstock.services.ArticleService;
import sn.ucad.gestionstock.services.MvtStkService;
import sn.ucad.gestionstock.validator.MvtStkValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {


    private MvtStkRepository mvtStkRepository;

    private ArticleService articleService;

  @Autowired
   public MvtStkServiceImpl(MvtStkRepository mvtStkRepository, ArticleService articleService) {
        this.mvtStkRepository = mvtStkRepository;
        this.articleService = articleService;
    }


    @Override
    public BigDecimal stockReelArticle(Long idArticle) {
        if (idArticle == null)
        {
            log.warn("Id Article is NULL");
            return BigDecimal.valueOf(-1) ;
        }
        articleService.findById(idArticle);
        return mvtStkRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Long idArticle) {
        return  mvtStkRepository.findAllByArticleId(idArticle)
                .stream().map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
     /* List<String>  errors = MvtStkValidator.validate(dto);

        if (!errors.isEmpty())
        {
            log.error("Mvt is not valide  {}", dto);
            throw  new InvalidEntityException("Le MvtStk n'est pas valide", ErrorCodes.MvtStk_NOT_VALID);

        }

        dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())));
        dto.setTypeMvt(TypeMvtStk.ENTRE);
        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );
*/ // Refactoring pour supprimer la duplication de code
     return  entreePositive(dto, TypeMvtStk.ENTRE);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {

       /* List<String>  errors = MvtStkValidator.validate(dto);

        if (!errors.isEmpty())
        {
            log.error("Mvt is not valide  {}", dto);
            throw  new InvalidEntityException("Le MvtStk n'est pas valide", ErrorCodes.MvtStk_NOT_VALID);

        }

        dto.setQuantite(
                BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue() * -1
        )));
        dto.setTypeMvt(TypeMvtStk.SORTIE);

        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );*/
       return  sortieNgative(dto,TypeMvtStk.SORTIE);
  }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto dto) {

       /* List<String>  errors = MvtStkValidator.validate(dto);

        if (!errors.isEmpty())
        {
            log.error("Mvt is not valide  {}", dto);
            throw  new InvalidEntityException("Le MvtStk n'est pas valide", ErrorCodes.MvtStk_NOT_VALID);

        }

        dto.setQuantite(
                BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue()
                )));
        dto.setTypeMvt(TypeMvtStk.CORRECTION_POS);

        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );  */
       return  entreePositive(dto,TypeMvtStk.CORRECTION_POS);
  }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {

      /*  List<String>  errors = MvtStkValidator.validate(dto);

        if (!errors.isEmpty())
        {
            log.error("Mvt is not valide  {}", dto);
            throw  new InvalidEntityException("Le MvtStk n'est pas valide", ErrorCodes.MvtStk_NOT_VALID);

        }

        dto.setQuantite(
                BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue() * -1
                )));
        dto.setTypeMvt(TypeMvtStk.CORRECTION_NEG);

        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );*/
      return sortieNgative(dto,TypeMvtStk.CORRECTION_NEG);
      }


    private MvtStkDto entreePositive(MvtStkDto dto, TypeMvtStk typeMvtStk) {
        List<String>  errors = MvtStkValidator.validate(dto);

        if (!errors.isEmpty())
        {
            log.error("Mvt is not valide  {}", dto);
            throw  new InvalidEntityException("Le MvtStk n'est pas valide", ErrorCodes.MvtStk_NOT_VALID);

        }

        dto.setQuantite(BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue())));
        dto.setTypeMvt(typeMvtStk);
        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );

    }



    private MvtStkDto sortieNgative(MvtStkDto dto, TypeMvtStk typeMvtStk) {

        List<String>  errors = MvtStkValidator.validate(dto);

        if (!errors.isEmpty())
        {
            log.error("Mvt is not valide  {}", dto);
            throw  new InvalidEntityException("Le MvtStk n'est pas valide", ErrorCodes.MvtStk_NOT_VALID);

        }

        dto.setQuantite(
                BigDecimal.valueOf(Math.abs(dto.getQuantite().doubleValue() * -1
                )));
        dto.setTypeMvt(typeMvtStk);

        return MvtStkDto.fromEntity(
                mvtStkRepository.save(MvtStkDto.toEntity(dto))
        );
    }

}
