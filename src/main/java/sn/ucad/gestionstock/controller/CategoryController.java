package sn.ucad.gestionstock.controller;

import org.springframework.web.bind.annotation.RestController;
import sn.ucad.gestionstock.controller.api.CategoryApi;
import sn.ucad.gestionstock.dto.CategoryDto;
import sn.ucad.gestionstock.services.CategoryService;

import java.util.List;


@RestController
public class CategoryController  implements CategoryApi {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryService.findById(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        return  categoryService.update(id,categoryDto);
    }


    @Override
    public CategoryDto findByCode(String code) {
        return categoryService.findByCode(code);
    }

    @Override
    public List<CategoryDto> findByDesignation(String designation) {
        return  categoryService.findByDesignation(designation);
    }

    @Override
    public void deleteById(Long id) {
       categoryService.deleteById(id);
    }
}
