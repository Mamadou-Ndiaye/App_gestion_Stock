package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "utilisateur")
    List<Roles> roles;

    @ManyToOne
    private  Entreprise entreprise;
}
