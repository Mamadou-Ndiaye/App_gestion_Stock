package sn.ucad.gestionstock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class Client extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

   // @Column(name = "nom")
    private String nom;

    private String prenom;
    // Adresse
    @Embedded  // Ce champs est un champs composé on pourra utiliser +sieurs fois dans les autres entités
    private  Adresse adresse;
    //private String adresse;

    private String photo;

    private String mail;

    private  String numTel;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient>  commandeClients;

    private Long idEntreprise;
}
