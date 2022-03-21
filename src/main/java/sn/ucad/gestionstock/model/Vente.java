package sn.ucad.gestionstock.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class Vente extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVente;

    private String code;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateVente;

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;

    private Long idEntreprise;
}
