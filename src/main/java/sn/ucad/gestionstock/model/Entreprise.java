package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class Entreprise  extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idEntreprise;

    // @Column(name = "nom")
    private String nom;

    //private String prenom;
    // Adresse
    @Embedded  // Ce champs est un champs composé on pourra utiliser +sieurs fois dans les autres entités
    private  Adresse adresse;
    //private String adresse;

    private String photo;

    private String mail;

    private  String numTel;

    private  String siteWeb;

    @OneToMany
    private List<Utilisateur>  utilisateurs;

}
