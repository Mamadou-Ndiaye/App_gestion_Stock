package sn.ucad.gestionstock.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;

import com.flickr4java.flickr.uploader.UploadMetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ucad.gestionstock.services.FlickrService;

import java.io.InputStream;


@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {


  /*  @Value("${flickr.apiKey}")
    private  String apiKey;

    @Value("${flickr.apiSecret}")
    private  String apiSecret;

    @Value("${flickr.appKey}")
    private  String appKey;

    @Value("${flickr.appSecret}")
    private  String appSecret;*/

    @Autowired
    private Flickr flickr;

    @Override
    public String savePhoto(InputStream inputStream, String title) throws FlickrException {
        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String photoID = flickr.getUploader().upload(inputStream,uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoID).getMedium640Url();
    }

   /* void connect()
    {
        Flickr flickr = new Flickr(apiKey,apiSecret, new REST());

        Auth auth = new Auth();

        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);

        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);

        flickr.setAuth(auth);
    }*/
}
