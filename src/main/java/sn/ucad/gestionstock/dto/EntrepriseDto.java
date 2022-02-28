package sn.ucad.gestionstock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Adresse;
import sn.ucad.gestionstock.model.Entreprise;


import javax.persistence.Embedded;
import java.util.List;

@Data
@Builder
public class EntrepriseDto {

    private  Long idEntreprise;

    // @Column(name = "nom")
    private String nom;

    //private String prenom;
    // Adresse
   // @Embedded  // Ce champs est un champs composé on pourra utiliser +sieurs fois dans les autres entités
    private AdresseDto adresseDto;
    //private String adresse;

    private String photo;

    private String mail;

    private  String numTel;

    private  String siteWeb;

    //@OneToMany
    @JsonIgnore
    private List<UtilisateurDto> utilisateurDtos;

    public  static EntrepriseDto  fromEntity(Entreprise entreprise)
    {
        if( entreprise == null)
        {
            return  null;
            // TODO
        }

        return  EntrepriseDto.builder()
                .idEntreprise(entreprise.getIdEntreprise())
                .nom(entreprise.getNom())
               // .adresseDto(entreprise.ge)
                .photo(entreprise.getPhoto())
                .mail(entreprise.getMail())
                .numTel(entreprise.getNumTel())
                .siteWeb(entreprise.getSiteWeb())
                //.utilisateurDtos(entreprise.getU)
                .build();
    }

    public  static  Entreprise toEntity(EntrepriseDto entrepriseDto)
    {
        if(entrepriseDto == null)
        {
            return  null;
            // TODO
        }

        return  Entreprise.builder()
                .idEntreprise(entrepriseDto.getIdEntreprise())
                .nom(entrepriseDto.getNom())
                .photo(entrepriseDto.getPhoto())
                .mail(entrepriseDto.getMail())
                .numTel(entrepriseDto.getNumTel())
                .siteWeb(entrepriseDto.getSiteWeb())
                .build();
    }
}
