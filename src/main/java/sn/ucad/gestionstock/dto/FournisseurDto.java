package sn.ucad.gestionstock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.CommandeFournisseur;
import sn.ucad.gestionstock.model.Fournisseur;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class FournisseurDto {


    private Long idFournisseur;

    private String nom;

    private String prenom;

    @Embedded
    private AdresseDto adresseDto;

    private String photo;

    private String mail;

    private  String numTel;

    //@OneToMany()
    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurDtos;

    public static FournisseurDto fromEntity(Fournisseur fournisseur)
    {
        if ( fournisseur == null)
        {
             return  null;
             // TODO
        }

        return  FournisseurDto.builder()
                .idFournisseur(fournisseur.getIdFournisseur())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresseDto(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .numTel(fournisseur.getNumTel())
                .build();
    }

    public static Fournisseur toEntity( FournisseurDto fournisseurDto)
    {
        if( fournisseurDto == null)
        {
            return  null;
            // TODO
        }

        return  Fournisseur.builder()
                .idFournisseur(fournisseurDto.getIdFournisseur())
                .nom(fournisseurDto.getNom())
                .prenom(fournisseurDto.getPrenom())
                .adresse(AdresseDto.toEntity(fournisseurDto.getAdresseDto()))
                .photo(fournisseurDto.getPhoto())
                .mail(fournisseurDto.getMail())
                .numTel(fournisseurDto.getNumTel())
                .build();

    }
}
