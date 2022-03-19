package sn.ucad.gestionstock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sn.ucad.gestionstock.dto.ArticleDto;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidOperationException;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.services.ArticleService;
import sn.ucad.gestionstock.services.FlickrService;

import java.io.InputStream;


@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements  Strategy<ArticleDto> {


    private FlickrService  flickrService;
    private ArticleService articleService;

    public SaveArticlePhoto(FlickrService flickrService, ArticleService articleService) {
        this.flickrService = flickrService;
        this.articleService = articleService;
    }

    @Override
    public ArticleDto savePhoto(Long id, InputStream photo, String title) throws FlickrException {

        ArticleDto articleDto = articleService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo,title);
        if (!StringUtils.hasLength(urlPhoto))
        {
            throw  new InvalidOperationException("Erreur lors de l'enregistrement du photo de l article ", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        articleDto.setPhoto(urlPhoto);

        return  articleService.save(articleDto);
    }
}
