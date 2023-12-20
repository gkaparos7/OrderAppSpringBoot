package gr.aueb.cf4.orderapp.dto;

import lombok.Data;

@Data
public class UserUpdateDTO extends BaseDTO {
    private String username;
    private String password;


    public UserUpdateDTO(Long id, String username, String password) {
        this.setId(id);
        this.username = username;
        this.password = password;
    }
}

