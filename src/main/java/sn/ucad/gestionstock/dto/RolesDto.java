package sn.ucad.gestionstock.dto;

import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Category;
import sn.ucad.gestionstock.model.Roles;
import sn.ucad.gestionstock.model.Utilisateur;

import javax.management.relation.Role;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;


@Data
@Builder
public class RolesDto {

    private Long idRole;

    private String roleName;

    private Long idEntreprise;

    // @ManyToOne
    // @JoinColumn(name = "idUtilisateur")
    private UtilisateurDto utilisateurDto;

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
                .utilisateurDto(UtilisateurDto.fromEntity(roles.getUtilisateur()))
                .build();
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
                .utilisateur(UtilisateurDto.toEntity(rolesDto.getUtilisateurDto()))
                .build();
    }
}
