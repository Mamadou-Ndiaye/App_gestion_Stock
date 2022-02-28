package sn.ucad.gestionstock.dto;

import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.model.Category;
import sn.ucad.gestionstock.model.MvtStk;

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

    private int typeMvt;

   // @ManyToOne
    // @JoinColumn(name = "idArticle")
    private ArticleDto articleDto;

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
               // .articleDto(mvtStk.getArticle())
                .build();
    }

    public static MvtStk fromEntity(MvtStkDto mvtStkDto)
    {
        if( mvtStkDto == null)
        {
            return  null;
        }

        return  MvtStk.builder()
                .idMvtStk(mvtStkDto.getIdMvtStk())
                .dateMvt(mvtStkDto.getDateMvt())
                .quantite(mvtStkDto.getQuantite())
                .typeMvt(mvtStkDto.getTypeMvt())
                .build();
    }

    public static MvtStkDto toEntity(MvtStk mvtStk)
    {
        if ( mvtStk == null)
        {
            return  null;
            // TODO
        }

        return  MvtStkDto.builder()
                .idMvtStk(mvtStk.getIdMvtStk())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .typeMvt(mvtStk.getTypeMvt())
                .build();
    }
}
