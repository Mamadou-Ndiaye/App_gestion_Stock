package sn.ucad.gestionstock.dto;

import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Builder
public class MvtStkDto {

    private Long idMvtStk;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date dateMvt;

    private BigDecimal quantite;

    private TypeMvtStk typeMvt;

    private Long idEntreprise;

    // @ManyToOne
    // @JoinColumn(name = "idArticle")
    private ArticleDto articleDto;

    private SourceMvtStk sourceMvt;


    public static MvtStkDto fromEntity(MvtStk mvtStk)
    {
        if( mvtStk == null)
        {
            return null;
        }

        return  MvtStkDto.builder()
                .idMvtStk(mvtStk.getIdMvtStk())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .typeMvt(mvtStk.getTypeMvt())
                .idEntreprise(mvtStk.getIdEntreprise())
                .sourceMvt(mvtStk.getSourceMvt())
               // .articleDto(mvtStk.getArticle())
                .build();
    }


    public static MvtStk toEntity(MvtStkDto mvtStk)
    {
        if ( mvtStk == null)
        {
            return  null;
            // TODO
        }

        return  MvtStk.builder()
                .idMvtStk(mvtStk.getIdMvtStk())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .typeMvt(mvtStk.getTypeMvt())
                .sourceMvt(mvtStk.getSourceMvt())
                .build();
    }
}
