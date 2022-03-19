package sn.ucad.gestionstock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.FournisseurDto;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidOperationException;
import sn.ucad.gestionstock.model.Fournisseur;
import sn.ucad.gestionstock.services.FlickrService;
import sn.ucad.gestionstock.services.FournisseurService;

import java.io.InputStream;


@Service("fournisseurStrategy")
@Slf4j
public class SaveFournisseurPhoto implements  Strategy<FournisseurDto> {


    FlickrService flickrService;
    FournisseurService fournisseurService;

    @Autowired
    public SaveFournisseurPhoto(FlickrService flickrService, FournisseurService fournisseurService) {
        this.flickrService = flickrService;
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {
        FournisseurDto fournisseurDto = fournisseurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo,title);
        if (!StringUtils.hasLength(urlPhoto))
        {
            throw  new InvalidOperationException("Erreur lors de l'enregistrement du photo du fournisseur ", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        fournisseurDto.setPhoto(urlPhoto);
        return  fournisseurService.save(fournisseurDto);
    }
}
