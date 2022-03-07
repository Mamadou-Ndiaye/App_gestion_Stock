package sn.ucad.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Category;
import sn.ucad.gestionstock.model.LigneVente;
import sn.ucad.gestionstock.model.Vente;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Builder
public class VenteDto {

    private Long idVente;

    private String code;

   // @Temporal(TemporalType.TIMESTAMP)
    private Date dateVente;

    private Long idEntreprise;

    //@OneToMany(mappedBy = "vente")
    @JsonIgnore
    private List<LigneVenteDto> ligneVenteDtos;

    public static VenteDto fromEntity(Vente vente)
    {
        if ( vente == null)
        {
            return  null;
            // TODO
        }

        return  VenteDto.builder()
                .idVente(vente.getIdVente())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .build();
    }

    public static Vente toEntity(VenteDto venteDto)
    {
        if (venteDto == null)
        {
            return null;
        }

        return  Vente.builder()
                .idVente(venteDto.getIdVente())
                .code(venteDto.getCode())
                .dateVente(venteDto.getDateVente())
                .build();
    }
}
