package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class Fournisseur extends  AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFournisseur;

    private String nom;

    private String prenom;

    @Embedded
    private  Adresse adresse;

    private String photo;

    private String mail;

    private  String numTel;

    @OneToMany()
    private List<CommandeFournisseur> commandeFournisseurs;

    private Long idEntreprise;
}
