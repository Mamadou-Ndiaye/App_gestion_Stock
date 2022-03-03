package sn.ucad.gestionstock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ucad.gestionstock.dto.CategoryDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.model.Category;
import sn.ucad.gestionstock.repository.CategoryRepository;
import sn.ucad.gestionstock.services.CategoryService;
import sn.ucad.gestionstock.validator.CategoryValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Transactional
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String>  errors = CategoryValidator.validate(categoryDto);

        if (!errors.isEmpty())
        {
            log.error("Category is not valide  {}", categoryDto);
            throw  new InvalidEntityException("La Category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID);

        }

        return  CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(categoryDto)));


    }

    @Override
    public CategoryDto findById(Long id) {

        if (id == null)
        {
            log.error("Category is NULL");
            return  null;
        }
         Optional<Category> categoryDto = categoryRepository.findById(id);

        return Optional.of(CategoryDto.fromEntity(categoryDto.get())).orElseThrow(()-> new EntityNotFoundException("Aucun category avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        if (id == null)
        {
            log.error("Category is NULL");
            return  null;
        }
        Category category = categoryRepository.findById(id).get();
         category.setCode(categoryDto.getCode());
         category.setDesignation(categoryDto.getDesignation());
         //category.setArticles(Arrays.asList(categoryDto.getArticleDtos()));

        return Optional.of(CategoryDto.fromEntity(category)).orElseThrow(()-> new EntityNotFoundException("Aucun category avec ID = "+ id + "n'a ete trouve dans la base de donnees",ErrorCodes.CATEGORY_NOT_FOUND));
    }


    @Override
    public List<CategoryDto> findAll() {
        return  categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findByCode(String code) {
        if ( code == null)
        {
            log.error("Category is NULL");
            return  null;
        }
        Optional<Category> categoryDto = categoryRepository.findByCode(code);

        return Optional.of(CategoryDto.fromEntity(categoryDto.get())).orElseThrow(()-> new EntityNotFoundException("Aucun Category avec code = "+ code + "n'a ete trouve dans la base de donnees",ErrorCodes.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findByDesignation(String designation) {
        if ( designation == null)
        {
            log.error("Category is NULL");
            return  null;
        }
        List<Category>  categories = categoryRepository.findByDesignation(designation);

        return categories.stream().map(CategoryDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if ( id == null)
        {
            log.error("Category is NULL");

        }

        categoryRepository.deleteById(id);
    }
}
