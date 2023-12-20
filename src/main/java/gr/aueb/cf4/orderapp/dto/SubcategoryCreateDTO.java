package gr.aueb.cf4.orderapp.dto;

import gr.aueb.cf4.orderapp.model.Category;
import lombok.Data;

@Data
public class SubcategoryCreateDTO {
    private String name;
    private Category category;

    public SubcategoryCreateDTO() {
    }

    public SubcategoryCreateDTO(String name, Category category) {
        this.name = name;
        this.category = category;
    }

}
