package sn.ucad.gestionstock.services.impl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sn.ucad.gestionstock.dto.CategoryDto;
import sn.ucad.gestionstock.exception.EntityNotFoundException;
import sn.ucad.gestionstock.exception.ErrorCodes;
import sn.ucad.gestionstock.exception.InvalidEntityException;
import sn.ucad.gestionstock.services.CategoryService;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public  class CategoryServiceImplTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void   shouldSaveCategoryWithSuccess()
    {
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("CAT 001")
                .designation("Des Test")
                .idEntreprise(1L)
                .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getIdCategory());

        assertEquals(expectedCategory.getCode() , savedCategory.getCode());
        assertEquals(expectedCategory.getDesignation() , savedCategory.getDesignation());
        assertEquals(expectedCategory.getIdEntreprise() , savedCategory.getIdEntreprise());
    }

    @Test
    public void   shoulUpdateCategoryWithSuccess()
    {
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("CAT 001")
                .designation("Des Test")
                .idEntreprise(1L)
                .build();

        CategoryDto savedCategory = categoryService.save(expectedCategory);

        CategoryDto categortToUpdate = savedCategory;
        categortToUpdate.setCode("CAT 002");

        savedCategory = categoryService.save(categortToUpdate);

        assertNotNull(categortToUpdate);
        assertNotNull(categortToUpdate.getIdCategory());

        assertEquals(categortToUpdate.getCode() , savedCategory.getCode());
        assertEquals(categortToUpdate.getDesignation() , savedCategory.getDesignation());
        assertEquals(categortToUpdate.getIdEntreprise() , savedCategory.getIdEntreprise());

    }

   /* @Test
    public void   shouldThrowInvalidEntitytexception()
    {
        CategoryDto expectedCategory = CategoryDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, ()-> categoryService.save(expectedCategory));

        assertEquals(ErrorCodes.CATEGORY_NOT_VALID, expectedException.getErrorCodes());
        assertEquals(1, expectedException.getErrors().size());
        assertEquals("Veuillez renseigner le code de la categorie", expectedException.getErrors().get(0));


    }*/

  /*  @Test(expected = EntityNotFoundException.class)
    public void   shouldThrowEntityNotFoundException()
    {
        categoryService.findById(0L);

    }

*/

}