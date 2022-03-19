package sn.ucad.gestionstock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidOperationException;

import java.io.InputStream;


@Service
@Slf4j
public class StrategyPhotoContext {

    private BeanFactory beanFactory;

    private  Strategy strategy;
    private  String context;

    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object  savePhoto(String context, Long id, InputStream photo, String title) throws FlickrException {
        determinerContext(context);
        return strategy.savePhoto(id, photo, title);
    }

    private  void  determinerContext(String context)
    {
        final String  beanName = context + "Strategy";
        switch (context)
        {
            case "article" :
            strategy  = beanFactory.getBean(beanName,SaveArticlePhoto.class);
                break;
            case "client" :
                strategy  = beanFactory.getBean(beanName,SaveClientPhoto.class);
                break;
            case "entreprise" :
                strategy  = beanFactory.getBean(beanName,SaveEntreprisePhoto.class);
                break;
            case "fournisseur" :
                strategy  = beanFactory.getBean(beanName,SaveFournisseurPhoto.class);
                break;
            case "utilisateur" :
                strategy  = beanFactory.getBean(beanName,SaveUtilisateurPhoto.class);
                break;
            default:
                throw  new InvalidOperationException("Context inconnue pour l enregistrement de la photo", ErrorCodes.UNKNOW_CONTEXT);
        }
    }

}
