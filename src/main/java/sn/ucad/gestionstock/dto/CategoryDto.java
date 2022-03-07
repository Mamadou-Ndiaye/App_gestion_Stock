package sn.ucad.gestionstock.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import sn.ucad.gestionstock.model.Article;
import sn.ucad.gestionstock.model.Category;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class CategoryDto {

    private Long idCategory;

    private String code;

    private String designation;

    private Long idEntreprise;

    // @OneToMany(mappedBy = "categoryDto")
    @JsonIgnore
    private List<ArticleDto> articleDtos;

    // Category --> CategoryDto
    public static  CategoryDto    fromEntity(Category category)
    {
        if (category == null)
        {
            return  null;
             // TODO throw an Excption
        }

        return  CategoryDto.builder()
                .idCategory(category.getIdCategory())
                .code(category.getCode())
                .idEntreprise(category.getIdEntreprise())
                .designation(category.getDesignation())
                .build();
    }

    // CategoryDto --> Category
    public static Category  toEntity(CategoryDto categoryDto)
    {
        if (categoryDto == null)
        {
              return  null;
            // TODO throw an Excption

        }


        /*

           Ou bien
           Category category = new Category();
           category.setIdCategory(categoryDto.getIdCategory());
           category.setCode(categoryDto.getCode());
           category.setDesignation(categoryDto.getDesignation());

           return  category;
           */


        return  Category.builder()
                .idCategory(categoryDto.getIdCategory())
                .code(categoryDto.getCode())
                .designation(categoryDto.getDesignation())
                .idEntreprise(categoryDto.getIdEntreprise())
                .build();
    }
}
