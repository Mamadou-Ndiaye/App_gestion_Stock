package sn.ucad.gestionstock.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class Roles extends  AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    private String roleName;

    @ManyToOne
   // @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;

    private Long idEntreprise;
}
