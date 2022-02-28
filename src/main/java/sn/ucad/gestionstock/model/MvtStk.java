package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class MvtStk extends  AbstractEntity {

    public static final int ENTREE = 1;

    public static final int SORTIE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMvtStk;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateMvt;

    private BigDecimal quantite;

    private int typeMvt;

    @ManyToOne
   // @JoinColumn(name = "idArticle")
    private Article article;

    private Long idEntreprise;
}
