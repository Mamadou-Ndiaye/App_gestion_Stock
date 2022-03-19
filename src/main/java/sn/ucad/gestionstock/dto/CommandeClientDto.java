package sn.ucad.gestionstock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Client;
import sn.ucad.gestionstock.model.CommandeClient;
import sn.ucad.gestionstock.model.EtatCommande;
import sn.ucad.gestionstock.model.LigneCommandeClient;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Long idCommandeClient;

    private String code;

    //@Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;

   // @ManyToOne
    //@JoinColumn(name = "idClient")
    private ClientDto clientDto;

    private EtatCommande etatCommande;

    private Long idEntreprise;


    //@OneToMany
    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClientDtos;


    public  static CommandeClientDto fromEntity(CommandeClient commandeClient)
    {
          if( commandeClient == null)
          {
              return  null;
              // TODO
          }

          return  CommandeClientDto.builder()
                  .idCommandeClient(commandeClient.getIdCommandeClient())
                  .code(commandeClient.getCode())
                  .dateCommande(commandeClient.getDateCommande())
                  .clientDto(ClientDto.fromEntity(commandeClient.getClient()))
                  .etatCommande(commandeClient.getEtatCommande())
                  .idEntreprise(commandeClient.getIdEntreprise())
                  .build();
    }

    public static   CommandeClient toEntity(CommandeClientDto commandeClientDto)
    {
        if(commandeClientDto == null)
        {
            return  null;
            // TODO
        }

        return  CommandeClient.builder()
                .idCommandeClient(commandeClientDto.getIdCommandeClient())
                .code(commandeClientDto.getCode())
                .dateCommande(commandeClientDto.getDateCommande())
                .etatCommande(commandeClientDto.getEtatCommande())
                .client(ClientDto.toEntity(commandeClientDto.getClientDto()))
                .client(ClientDto.toEntity(commandeClientDto.getClientDto()))
                .idEntreprise(commandeClientDto.getIdEntreprise())
                .build();

    }

    public  boolean isCommandeClientLivree()
    {
        return  EtatCommande.LIVREE.equals(this.etatCommande);
    }

}
