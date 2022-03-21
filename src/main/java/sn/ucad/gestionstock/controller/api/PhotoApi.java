package sn.ucad.gestionstock.controller.api;

import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

@Api(APP_ROOT + "/photos")
public interface PhotoApi {

    @PostMapping(APP_ROOT + "/photos/{id}/{titre}/{context}")
    Object  savePhoto(@PathVariable("context") String context, @PathVariable("id") Long id, @RequestPart("file") MultipartFile photo, @PathVariable("title") String title) throws IOException, FlickrException;

}
