package sn.ucad.gestionstock.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;



@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Data @ToString
@EqualsAndHashCode(callSuper = true)
public class Category  extends  AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    private String code;

    private String designation;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    private Long idEntreprise;
}
