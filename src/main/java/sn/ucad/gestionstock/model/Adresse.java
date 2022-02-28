package sn.ucad.gestionstock.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
//@Entity
@Embeddable
public class Adresse {

    private  String adresse1;

    private  String adresse2;

    private  String ville;

    private  String codePostal;

    private  String pays;
}
