package sn.ucad.gestionstock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.ClientDto;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidOperationException;
import sn.ucad.gestionstock.model.Client;
import sn.ucad.gestionstock.services.ClientService;
import sn.ucad.gestionstock.services.FlickrService;

import java.io.InputStream;


@Service("clientStrategy")
@Slf4j
public class SaveClientPhoto implements  Strategy<ClientDto> {

      ClientService clientService;
      FlickrService flickrService;

      @Autowired
    public SaveClientPhoto(ClientService clientService, FlickrService flickrService) {
        this.clientService = clientService;
        this.flickrService = flickrService;
    }

    @Override
    public ClientDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {

          ClientDto clientDto = clientService.findById(id);
          String urlPhoto = flickrService.savePhoto(photo,title);

        if (!StringUtils.hasLength(urlPhoto))
        {
            throw  new InvalidOperationException("Erreur lors de l'enregistrement du photo de l article ", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        clientDto.setPhoto(urlPhoto);

        return  clientService.save(clientDto);
    }
}
