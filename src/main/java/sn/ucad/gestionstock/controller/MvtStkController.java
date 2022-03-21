package sn.ucad.gestionstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.MvtStkApi;
import sn.ucad.gestionstock.dto.MvtStkDto;
import sn.ucad.gestionstock.services.MvtStkService;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class MvtStkController implements MvtStkApi {

    MvtStkService mvtStkService;

    @Autowired
    public MvtStkController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }

    @Override
    public BigDecimal stockReelArticle(Long idArticle) {
        return mvtStkService.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Long idArticle) {
        return mvtStkService.mvtStkArticle(idArticle);
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return mvtStkService.entreeStock(dto);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return mvtStkService.sortieStock(dto);
    }

    @Override
    public MvtStkDto correctionStockPos(MvtStkDto dto) {
        return mvtStkService.correctionStockPos(dto);
    }

    @Override
    public MvtStkDto correctionStockNeg(MvtStkDto dto) {
        return mvtStkService.correctionStockNeg(dto);
    }
}
