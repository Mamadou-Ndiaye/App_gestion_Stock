package sn.ucad.gestionstock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.CommandeFournisseur;
import sn.ucad.gestionstock.model.Fournisseur;
import sn.ucad.gestionstock.model.LigneCommandeFournisseur;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Long idCommandeFournisseur;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;

    //@ManyToOne
    private FournisseurDto fournisseurDto;

    private Long idEntreprise;


    //@OneToMany
    @JsonIgnore
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurDtos;


    public  static CommandeFournisseurDto  fromEntity(CommandeFournisseur commandeFournisseur)
    {
        if(commandeFournisseur == null)
        {
            return  null;
            // TODO
        }

        return  CommandeFournisseurDto.builder()
                .idCommandeFournisseur(commandeFournisseur.getIdCommandeFournisseur())
                .dateCommande(commandeFournisseur.getDateCommande())
               // .fournisseurDto(commandeFournisseur.getFournisseur())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto)
    {
        if( commandeFournisseurDto == null)
        {
            return  null;
            // TODO
        }

        return  CommandeFournisseur.builder()
                .idCommandeFournisseur(commandeFournisseurDto.getIdCommandeFournisseur())
                .dateCommande(commandeFournisseurDto.getDateCommande())
                .build();
    }
}
