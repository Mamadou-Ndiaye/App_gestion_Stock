package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(callSuper = true)
public class Utilisateur extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    private String nom;

    private String prenom;

    private String mail;

    private String motDePasse;

    private String photo;

    private boolean actived;

    @Embedded
    private  Adresse adresse;

    private Date dateDeNaissance;


    @ManyToMany(fetch = FetchType.LAZY)
    List<Roles> roles = new ArrayList<>();

    @ManyToOne
    private  Entreprise entreprise;
}
