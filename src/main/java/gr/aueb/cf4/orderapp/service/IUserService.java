package gr.aueb.cf4.orderapp.service;


import gr.aueb.cf4.orderapp.dto.UserCreateDTO;
import gr.aueb.cf4.orderapp.dto.UserReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.UserUpdateDTO;
import gr.aueb.cf4.orderapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService  extends UserDetailsService {
    UserReadOnlyDTO createUser(UserCreateDTO userCreateDTO);

    UserReadOnlyDTO updateUser(Long userId, UserUpdateDTO userUpdateDTO);

    List<UserReadOnlyDTO> getAllUsers();

    Optional<User> getUserById(Long userId);

    UserReadOnlyDTO deleteUser(Long userId);

    User getUserEntityById(Long userId);

    User getCurrentUser();
}

