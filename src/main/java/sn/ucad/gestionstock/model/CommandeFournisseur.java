package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class CommandeFournisseur extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommandeFournisseur;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;

    @ManyToOne
    private  Fournisseur fournisseur;

    @OneToMany
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    private Long idEntreprise;
}
