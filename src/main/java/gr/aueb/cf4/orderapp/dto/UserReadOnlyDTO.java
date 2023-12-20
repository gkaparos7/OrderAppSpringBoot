package gr.aueb.cf4.orderapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReadOnlyDTO extends BaseDTO {
    private String username;


    public UserReadOnlyDTO(Long id, String username) {
        super(id);
        this.username = username;

    }
}
