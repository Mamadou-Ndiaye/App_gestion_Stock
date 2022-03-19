package sn.ucad.gestionstock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.UtilisateurDto;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidOperationException;
import sn.ucad.gestionstock.model.Utilisateur;
import sn.ucad.gestionstock.services.FlickrService;
import sn.ucad.gestionstock.services.UtilisateurService;

import java.io.InputStream;


@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements  Strategy<UtilisateurDto> {

    FlickrService flickrService;
    UtilisateurService utilisateurService;

    @Autowired
    public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
        this.flickrService = flickrService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {
        UtilisateurDto utilisateurDto = utilisateurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo,title);
        if (!StringUtils.hasLength(urlPhoto))
        {
            throw  new InvalidOperationException("Erreur lors de l'enregistrement du photo de l'utilisateur ", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        utilisateurDto.setPhoto(urlPhoto);
        return  utilisateurService.save(utilisateurDto);
    }
}
