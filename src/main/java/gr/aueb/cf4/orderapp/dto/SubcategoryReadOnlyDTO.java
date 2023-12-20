package gr.aueb.cf4.orderapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gr.aueb.cf4.orderapp.model.Product;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubcategoryReadOnlyDTO extends BaseDTO {
    private String name;
    private String photoUrl;
    private List<Product> products;
    private CategoryReadOnlyDTO category;

    public SubcategoryReadOnlyDTO() {
    }

    public SubcategoryReadOnlyDTO(Long id, String name, String photoUrl, List<Product> products, CategoryReadOnlyDTO category) {
        this.setId(id);
        this.name = name;
        this.photoUrl = photoUrl;
        this.products = products;
        this.category = category;
    }

    public SubcategoryReadOnlyDTO(Long id, String name, String photoUrl, List<Product> products) {
        super(id);
        this.name = name;
        this.photoUrl = photoUrl;
        this.products = products;
    }

    public SubcategoryReadOnlyDTO(Long id, String name, List<Product> products) {
        this.setId(id);
        this.name = name;
        this.products = products;
    }

    public SubcategoryReadOnlyDTO(Long id, String name) {
        super(id);
        this.name = name;
    }
}

