package sn.ucad.gestionstock.controller.api;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ucad.gestionstock.dto.CategoryDto;

import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById( @PathVariable("idCategory") Long id);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @PutMapping(value =  APP_ROOT+ "/categories/update/{idCategory}")
    CategoryDto update(@PathVariable("idCategory") Long id, @RequestBody CategoryDto categoryDto);


    @GetMapping(value = APP_ROOT + "/categories/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode(@PathVariable("code") String code);

    @GetMapping(value = APP_ROOT + "/categories/byDesignation", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findByDesignation(@Param("designation") String designation);

    @DeleteMapping(value =  APP_ROOT + "/categories/delete/{idCategory}")
    void  deleteById(@PathVariable("idCategory") Long id);
}
