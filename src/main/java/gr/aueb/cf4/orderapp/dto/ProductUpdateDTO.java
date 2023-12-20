package gr.aueb.cf4.orderapp.dto;

import gr.aueb.cf4.orderapp.model.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProductUpdateDTO extends BaseDTO{
    private String name;
    private String photoUrl;
    private List<Size> sizes;
    private Long subcategoryId;

    public ProductUpdateDTO(Long id, String name, String photoUrl, List<Size> sizes, Long subcategoryId) {
        this.setId(id);
        this.name = name;
        this.photoUrl = photoUrl;
        this.sizes = sizes;
        this.subcategoryId = subcategoryId;
    }
}
