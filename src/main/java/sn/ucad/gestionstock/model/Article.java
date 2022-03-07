package sn.ucad.gestionstock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Article extends  AbstractEntity {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHT;  // prix Unitaire Hors TVA

    private BigDecimal tauxTva; // taux TVA pour Chaqe Article

    private BigDecimal prixUnitaireTTC;

    private String photo;

    private Long idEntreprise;

   // @JoinColumn(name = "idCategory")
    @ManyToOne
    private  Category category;

    @OneToMany
    private List<LigneVente>  ligneVentes;

    @OneToMany
    private  List<LigneCommandeClient> ligneCommandeClients;

    @OneToMany
    private  List<LigneCommandeFournisseur>  ligneCommandeFournisseurs;

    @OneToMany
    private  List<MvtStk> mvtStks;
}
