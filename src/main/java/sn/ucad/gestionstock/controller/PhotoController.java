package sn.ucad.gestionstock.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sn.ucad.gestionstock.controller.api.PhotoApi;
import sn.ucad.gestionstock.services.strategy.StrategyPhotoContext;

import java.io.File;
import java.io.IOException;

@RestController
public class PhotoController implements PhotoApi {

   private StrategyPhotoContext strategyPhotoContext;

   @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @Override
    public Object savePhoto(String context, Long id, MultipartFile photo, String title) throws IOException {
        return  strategyPhotoContext.savePhoto(context,id,photo.getInputStream(),title;
    }
}
