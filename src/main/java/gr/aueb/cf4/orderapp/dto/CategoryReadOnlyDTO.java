package gr.aueb.cf4.orderapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gr.aueb.cf4.orderapp.model.Subcategory;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryReadOnlyDTO extends BaseDTO {

    private String name;
    private String photoUrl;
    private List<Subcategory> subcategories;

    public CategoryReadOnlyDTO() {
    }

    public CategoryReadOnlyDTO(Long id, String name, String photoUrl, List<Subcategory> subcategories) {
        this.setId(id);
        this.name = name;
        this.photoUrl = photoUrl;
        this.subcategories = subcategories;
    }
}

