package gr.aueb.cf4.orderapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import gr.aueb.cf4.orderapp.model.Size;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductReadOnlyDTO extends BaseDTO{
    private String name;
    private String photoUrl;
    private List<Size> sizes;
    private SubcategoryReadOnlyDTO subcategory;

    public ProductReadOnlyDTO() {
    }

    public ProductReadOnlyDTO(Long id, String name, String photoUrl, List<Size> sizes, SubcategoryReadOnlyDTO subcategory) {
        this.setId(id);
        this.name = name;
        this.photoUrl = photoUrl;
        this.sizes = sizes;
        this.subcategory = subcategory;
    }
}
