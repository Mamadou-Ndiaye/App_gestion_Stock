package sn.ucad.gestionstock.services;

import sn.ucad.gestionstock.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto findById(Long id);

    CategoryDto update(Long id, CategoryDto categoryDto);

    List<CategoryDto>  findAll();

    CategoryDto findByCode(String code);

    List<CategoryDto> findByDesignation(String designation);

    void  deleteById(Long id);
}
