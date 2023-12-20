package gr.aueb.cf4.orderapp.dto;

import lombok.Data;

@Data
public class CategoryUpdateDTO extends BaseDTO{
    private String name;
    private String photoUrl;
    public CategoryUpdateDTO(Long id, String name, String photoUrl) {
        this.setId(id);
        this.name = name;
        this.photoUrl = photoUrl;
    }
}
