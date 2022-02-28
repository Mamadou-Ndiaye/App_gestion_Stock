package sn.ucad.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sn.ucad.gestionstock.model.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Builder
public class UtilisateurDto {

    private Long idUtilisateur;

    private String nom;

    private String prenom;

    private String mail;

    private String motDePasse;

    private String photo;

    private  AdresseDto adresseDto;

    private Date dateDeNaissance;

    private boolean  actived;


    //@OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    List<RolesDto> rolesDtos;

    //@ManyToOne
    private EntrepriseDto entrepriseDto;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur)
    {
         if (utilisateur == null)
         {
              return  null;
              // TODO
         }

         return  UtilisateurDto.builder()
                 .idUtilisateur(utilisateur.getIdUtilisateur())
                 .nom(utilisateur.getNom())
                 .prenom(utilisateur.getPrenom())
                 .mail(utilisateur.getMail())
                 .dateDeNaissance(utilisateur.getDateDeNaissance())
                 .motDePasse(utilisateur.getMotDePasse())
                 .photo(utilisateur.getPhoto())
                 .adresseDto(AdresseDto.fromEntity(utilisateur.getAdresse()))
                 //.actived(utilisateur.getActived())
                 .entrepriseDto(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                 .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto)
    {
        if ( utilisateurDto == null)
        {
            return  null;
            // TODO
        }

        return Utilisateur.builder()
                .idUtilisateur(utilisateurDto.getIdUtilisateur())
                .nom(utilisateurDto.getNom())
                .prenom(utilisateurDto.getPrenom())
                .mail(utilisateurDto.getMail())
                .motDePasse(utilisateurDto.getMotDePasse())
                .photo(utilisateurDto.getPhoto())
                .dateDeNaissance(utilisateurDto.getDateDeNaissance())
               .adresse(AdresseDto.toEntity(utilisateurDto.getAdresseDto()))
                .entreprise(EntrepriseDto.toEntity(utilisateurDto.getEntrepriseDto()))
               // .actived(utilisateurDto.getActived())
                .build();
    }
}
