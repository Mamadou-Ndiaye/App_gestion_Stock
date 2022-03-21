package sn.ucad.gestionstock.dto;


import lombok.Builder;
import lombok.Data;

@Data@Builder
public class ChangerMotDePasseUtilisateurDto {

    private  Long id;

    private  String motDePasse;

    private  String confirmMotDePasse;
}
