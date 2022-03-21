package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class LigneCommandeFournisseur  extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneCdeFrs;

    @ManyToOne
    //@JoinColumn(name = "idArticle")
    private Article article;

    private BigDecimal quantite;

    @ManyToOne
   // @JoinColumn(name = "idCommandeFournisseur")
    private CommandeFournisseur commandeFournisseur;

    private Long idEntreprise;

}
