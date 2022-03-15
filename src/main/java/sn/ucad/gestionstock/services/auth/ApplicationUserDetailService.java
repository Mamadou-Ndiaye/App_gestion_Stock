package sn.ucad.gestionstock.services.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.model.Utilisateur;
//import sn.ucad.gestionstock.model.auth.ExtendUser;
import sn.ucad.gestionstock.repository.UtilisateurRepository;

import java.util.*;

@Service
public class  ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

         Utilisateur utilisateur = utilisateurRepository.findByMail(mail).orElseThrow(()->
                 new EntityNotFoundException("Aucun Utilisateur avec le mail furni", ErrorCodes.UTILISATEUR_NOT_FOUND));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(roles -> {authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));});


       // return new ExtendUser( utilisateur.getMail(), utilisateur.getMotDePasse(), authorities, utilisateur.getEntreprise().getIdEntreprise());
      // Soit Je retourne cettte utilisateur directement, si on pas besoin de idEntreprise raison pour laquel j ai cree la classe ExtendUser
        return new User(utilisateur.getMail(),utilisateur.getMotDePasse(), authorities);
    }
}
