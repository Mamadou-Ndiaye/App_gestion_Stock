package sn.ucad.gestionstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.CommandeFournisseurApi;
import sn.ucad.gestionstock.dto.CommandeFournisseurDto;
import sn.ucad.gestionstock.services.CommandeFournisseurService;

import java.util.List;

@RestController
public class CommandeFournisseurController  implements CommandeFournisseurApi {

    private CommandeFournisseurService fournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        return fournisseurService.save(commandeFournisseurDto);
    }

    @Override
    public CommandeFournisseurDto findById(Long id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return   fournisseurService.findAll();
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        return fournisseurService.findByCode(code);
    }

    @Override
    public void deleteById(Long id) {
               fournisseurService.deleteById(id);
    }
}
