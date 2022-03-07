package sn.ucad.gestionstock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Adresse;
import sn.ucad.gestionstock.model.Client;
import sn.ucad.gestionstock.model.CommandeClient;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class ClientDto {

    private Long idClient;

    // @Column(name = "nom")
    private String nom;

    private String prenom;
    // Adresse
   // @Embedded  // Ce champs est un champs composé on pourra utiliser +sieurs fois dans les autres entités
    private AdresseDto adresseDto;

    //private String adresse;

    private String photo;

    private String mail;

    private  String numTel;

    private Long idEntreprise;

    // @OneToMany(mappedBy = "clientDto")
    @JsonIgnore
    private List<CommandeClientDto> commandeClientDtos;

    public static ClientDto  fromEntity(Client client)
    {
        if (client == null)
        {
            return  null;
            // TODO
        }

        return  ClientDto.builder()
                .idClient(client.getIdClient())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .adresseDto(AdresseDto.fromEntity(client.getAdresse()))
                .photo(client.getPhoto())
                .mail(client.getMail())
                .numTel(client.getNumTel())
                .build();
    }

    public static  Client  toEntity(ClientDto clientDto)
    {
        if (clientDto == null)
        {
            return  null;
            // TODO
        }

        return  Client.builder()
                .idClient(clientDto.getIdClient())
                .nom(clientDto.getNom())
                .prenom(clientDto.getPrenom())
                //.adresse(ClientDto.toEntity(clientDto.getAdresseDto()))
                .photo(clientDto.getPhoto())
                .mail(clientDto.getMail())
                .numTel(clientDto.getNumTel())
                .build();
    }
}
