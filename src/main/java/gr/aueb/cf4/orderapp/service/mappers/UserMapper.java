package gr.aueb.cf4.orderapp.service.mappers;

import gr.aueb.cf4.orderapp.dto.UserReadOnlyDTO;
import gr.aueb.cf4.orderapp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserReadOnlyDTO convertToUserReadOnlyDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserReadOnlyDTO(user.getId(), user.getUsername());
    }

    public User convertToUserEntity(UserReadOnlyDTO userReadOnlyDTO) {
        if (userReadOnlyDTO == null) {
            return null;
        }

        User user = new User();
        user.setId(userReadOnlyDTO.getId());
        user.setUsername(userReadOnlyDTO.getUsername());

        return user;
    }
}
