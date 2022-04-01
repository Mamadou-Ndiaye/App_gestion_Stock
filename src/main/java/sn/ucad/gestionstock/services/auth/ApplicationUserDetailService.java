package sn.ucad.gestionstock.services.auth;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.gestionstock.dto.UtilisateurDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.model.Utilisateur;
//import sn.ucad.gestionstock.model.auth.ExtendUser;
import sn.ucad.gestionstock.repository.UtilisateurRepository;
import sn.ucad.gestionstock.services.UtilisateurService;

import java.util.*;


//@Transactional
@Slf4j
@Service
public class  ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;


   /* private  final  UtilisateurService utilisateurService;

    public ApplicationUserDetailService(@Lazy UtilisateurService utilisateurService) {
        super();
        this.utilisateurService = utilisateurService;
    }*/

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

       // UtilisateurDto utilisateur = utilisateurService.findByMail(mail);


         Utilisateur utilisateur = utilisateurRepository.findByMail(mail).orElseThrow(()->
                 new EntityNotFoundException("Aucun Utilisateur avec le mail fourni : ", ErrorCodes.UTILISATEUR_NOT_FOUND));


        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
       // System.out.println("*****  Roles ROLES ******"+utilisateur.getRoles());
        utilisateur.getRoles().forEach(r -> {authorities.add(new SimpleGrantedAuthority(r.getRoleName()));});

        log.info("*** AUTHORIZATIONS 1 *******" + authorities.get(0));
        log.info("*****  Roles ROLES ****** "+ utilisateur.getRoles() + " Taille de la liste  " + authorities.size());


        // return new ExtendUser( utilisateur.getMail(), utilisateur.getMotDePasse(), authorities, utilisateur.getEntreprise().getIdEntreprise());
      // Soit Je retourne cettte utilisateur directement, si on pas besoin de idEntreprise raison pour laquel j ai cree la classe ExtendUser
        return new User(utilisateur.getMail(),utilisateur.getMotDePasse(), authorities);
    }
    
}
