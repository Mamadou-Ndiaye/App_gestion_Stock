package sn.ucad.gestionstock.config;


import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.Auth;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Configuration
public class FlickrConfiguration {

    @Value("${flickr.apiKey}")
    private  String apiKey;

    @Value("${flickr.apiSecret}")
    private  String apiSecret;

    @Bean
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr = new Flickr(apiKey,apiSecret, new REST());

        OAuthService service = new ServiceBuilder(apiKey)
                .apiKey(apiKey)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));

        final Scanner scanner = new Scanner(System.in);

        final OAuth1RequestToken requestToken = ((OAuth10aService) service).getRequestToken();

          final  String  authUrl = ((OAuth10aService) service).getAuthorizationUrl(requestToken);

        System.out.println(authUrl);

        System.out.println("PAste it here :::");

        final  String authVerifier = scanner.nextLine();

        OAuth1AccessToken auth1AccessToken = ((OAuth10aService) service).getAccessToken(requestToken,authVerifier);

        System.out.println(auth1AccessToken.getToken());
        System.out.println(auth1AccessToken.getTokenSecret());

        Auth auth = flickr.getAuthInterface().checkToken(auth1AccessToken);

        System.out.println("===========================================");
        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());

        return flickr;
    }

}
