package sn.ucad.gestionstock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;



@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class LigneCommandeClient extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneCdeClt;

    //@JoinColumn(name = "idArticle")
    @ManyToOne
    private Article article;

    @ManyToOne
   // @JoinColumn(name = "idCommandeClient")
    private CommandeClient commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Long idEntreprise;
}
