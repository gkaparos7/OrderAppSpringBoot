package gr.aueb.cf4.orderapp.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public abstract class BaseDTO {

    @NotNull
    private Long id;

    public BaseDTO() {
    }

    public BaseDTO(Long id) {
        this.id = id;
    }
}
