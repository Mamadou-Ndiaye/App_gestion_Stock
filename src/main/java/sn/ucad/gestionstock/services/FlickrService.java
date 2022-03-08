package sn.ucad.gestionstock.services;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface FlickrService {

    String savePhoto(InputStream inputStream , String title) throws FlickrException;
}
