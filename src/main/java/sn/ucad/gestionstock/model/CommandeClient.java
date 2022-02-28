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
public class CommandeClient extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommandeClient;

    private String code;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;

    @ManyToOne
    //@JoinColumn(name = "idClient")
    private Client client;

    @OneToMany
    private List<LigneCommandeClient>  ligneCommandeClients;

    private Long idEntreprise;
}
