package sn.ucad.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import sn.ucad.gestionstock.model.*;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
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

    private Long idEntreprise;


    //@OneToMany(mappedBy = "utilisateur")
    //@JsonIgnore
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

        UtilisateurDto  utilisateurDto = new UtilisateurDto();
        utilisateurDto.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDto.setMail(utilisateur.getMail());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setActived(utilisateur.isActived());
        utilisateurDto.setMotDePasse(utilisateur.getMotDePasse());
        utilisateurDto.setPhoto(utilisateur.getPhoto());
        utilisateurDto.setAdresseDto(AdresseDto.fromEntity(utilisateur.getAdresse()));
        //List<RolesDto> roles = RolesDto.fromEntity(utilisateur.getRoles());
        utilisateurDto.setRolesDtos(RolesDto.fromEntity(utilisateur.getRoles()));
        // utilisateurDto.setRolesDtos(roles);
        utilisateurDto.setDateDeNaissance(utilisateur.getDateDeNaissance());

        return  utilisateurDto;

         /*return  UtilisateurDto.builder()
                 .idUtilisateur(utilisateur.getIdUtilisateur())
                 .nom(utilisateur.getNom())
                 .prenom(utilisateur.getPrenom())
                 .mail(utilisateur.getMail())
                 .dateDeNaissance(utilisateur.getDateDeNaissance())
                 .motDePasse(utilisateur.getMotDePasse())
                 .photo(utilisateur.getPhoto())
                 //.rolesDtos(RolesDto.fromEntity(utilisateur.getRoles()));
                 .adresseDto(AdresseDto.fromEntity(utilisateur.getAdresse()))
                 .actived(utilisateur.isActived())
                 .entrepriseDto(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                 .build();*/
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto)
    {
        if ( utilisateurDto == null)
        {
            return  null;
            // TODO
        }

        Utilisateur  utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(utilisateurDto.getIdUtilisateur());
        utilisateur.setMail(utilisateurDto.getMail());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setActived(utilisateurDto.isActived());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresseDto()));
        //List<Roles> roles = RolesDto.toEntity(utilisateurDto.getRolesDtos());
        utilisateur.setRoles(RolesDto.toEntity(utilisateurDto.getRolesDtos()));
        // utilisateur.setRoles(roles);
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());

        return  utilisateur;

       /* return Utilisateur.builder()
                .idUtilisateur(utilisateurDto.getIdUtilisateur())
                .nom(utilisateurDto.getNom())
                .prenom(utilisateurDto.getPrenom())
                .mail(utilisateurDto.getMail())
                .motDePasse(utilisateurDto.getMotDePasse())
                .photo(utilisateurDto.getPhoto())
                .dateDeNaissance(utilisateurDto.getDateDeNaissance())
                .adresse(AdresseDto.toEntity(utilisateurDto.getAdresseDto()))
                .roles(RolesDto.toEntity(utilisateurDto.getRolesDtos())
                .entreprise(EntrepriseDto.toEntity(utilisateurDto.getEntrepriseDto()))
                .actived(utilisateurDto.isActived())
                .build();*/
    }
}
