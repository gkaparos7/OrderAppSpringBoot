package gr.aueb.cf4.orderapp.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String username;
    private String password;


    public UserCreateDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

