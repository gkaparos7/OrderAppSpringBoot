package gr.aueb.cf4.orderapp.dto;

import gr.aueb.cf4.orderapp.model.Category;
import lombok.Data;

@Data
public class SubcategoryUpdateDTO extends BaseDTO{
    private String name;
    private Category category;

    public SubcategoryUpdateDTO(Long id, String name, Category category) {
        this.setId(id);
        this.name = name;
        this.category = category;
    }
}
