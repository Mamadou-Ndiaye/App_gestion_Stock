package sn.ucad.gestionstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import sn.ucad.gestionstock.services.ArticleService;

public class ArticleController {

    //@Qualifier("nomDuService a appeler")
    @Autowired
    private ArticleService articleService;
}
