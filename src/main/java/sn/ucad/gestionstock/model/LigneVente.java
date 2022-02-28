package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.*;


@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class LigneVente extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLigneVente;

    //@JoinColumn(name = "idArticle")
    @ManyToOne
    private Article article;

    // @JoinColumn(name = "vente")
    @ManyToOne
    private Vente vente;

    private Long idEntreprise;

}
