package sn.ucad.gestionstock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.EntrepriseDto;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidOperationException;
import sn.ucad.gestionstock.model.Entreprise;
import sn.ucad.gestionstock.services.EntrepriseService;
import sn.ucad.gestionstock.services.FlickrService;

import java.io.InputStream;


@Service("entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements  Strategy<EntrepriseDto> {

    FlickrService flickrService;
    EntrepriseService entrepriseService;

    @Autowired
    public SaveEntreprisePhoto(FlickrService flickrService, EntrepriseService entrepriseService) {
        this.flickrService = flickrService;
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {
        EntrepriseDto entrepriseDto = entrepriseService.findById(id);
        String urlPhoto =flickrService.savePhoto(photo,title);
        if (!StringUtils.hasLength(urlPhoto))
        {
            throw  new InvalidOperationException("Erreur lors de l'enregistrement du photo de l'entreprise ", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        entrepriseDto.setPhoto(urlPhoto);

        return  entrepriseService.save(entrepriseDto);
    }
}
