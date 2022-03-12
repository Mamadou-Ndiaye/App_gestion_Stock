package sn.ucad.gestionstock.model.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;



public class ExtendUser extends User {
    @Getter
    @Setter
    private  Long idEntreprise;


    public ExtendUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ExtendUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long idEntreprise) {
        super(username, password, authorities);
        this.idEntreprise = idEntreprise;
    }




}
