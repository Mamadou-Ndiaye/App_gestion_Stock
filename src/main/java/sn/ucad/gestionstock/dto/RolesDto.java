package sn.ucad.gestionstock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ucad.gestionstock.model.Category;
import sn.ucad.gestionstock.model.Roles;
import sn.ucad.gestionstock.model.Utilisateur;

import javax.management.relation.Role;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {

    private Long idRole;

    private String roleName;

    private Long idEntreprise;

    // @ManyToOne
    // @JoinColumn(name = "idUtilisateur")

    // private UtilisateurDto utilisateurDto;

    public static RolesDto fromEntity(Roles roles)
    {
        if ( roles == null)
        {
            return  null;
            // TODO
        }

        return  RolesDto.builder()
                .idRole(roles.getIdRole())
                .roleName(roles.getRoleName())
                //.utilisateurDto(UtilisateurDto.fromEntity(roles.getUtilisateur()))
                //.idEntreprise(roles.getIdEntreprise())
                .build();
    }

    public static List<RolesDto> fromEntity(List<Roles> roles)
    {
        if ( roles == null)
        {
            return  null;
            // TODO
        }

        return roles.stream().map(role ->fromEntity(role) ).collect(Collectors.toList());
    }

    public static Roles toEntity(RolesDto rolesDto)
    {
        if( rolesDto == null)
        {
            return  null;
        }

        return  Roles.builder()
                .idRole(rolesDto.getIdRole())
                .roleName(rolesDto.getRoleName())
               // .utilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateurDto()))
              //  .idEntreprise(rolesDto.getIdEntreprise())
                .build();
    }

    public static List<Roles> toEntity(List<RolesDto> rolesDto)
    {
        if( rolesDto == null)
        {
            return  null;
        }

        return  rolesDto.stream().map(roleDto->toEntity(roleDto)).collect(Collectors.toList());
    }
}
