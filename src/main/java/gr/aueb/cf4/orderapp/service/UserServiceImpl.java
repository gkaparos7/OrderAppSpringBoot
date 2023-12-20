package gr.aueb.cf4.orderapp.service;


import gr.aueb.cf4.orderapp.dto.UserCreateDTO;
import gr.aueb.cf4.orderapp.dto.UserReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.UserUpdateDTO;
import gr.aueb.cf4.orderapp.model.User;
import gr.aueb.cf4.orderapp.model.Wishlist;
import gr.aueb.cf4.orderapp.repository.UserRepository;
import gr.aueb.cf4.orderapp.repository.WishlistRepository;
import gr.aueb.cf4.orderapp.security.JwtTokenProvider;
import gr.aueb.cf4.orderapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf4.orderapp.service.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import static gr.aueb.cf4.orderapp.security.JwtAuthenticationFilter.getJwtFromRequest;


@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, WishlistRepository wishlistRepository, UserMapper userMapper, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
        this.userMapper = userMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public UserReadOnlyDTO createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());

        User savedUser = userRepository.save(user);

        // Create wishlist and associate it with the user
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(savedUser);
        wishlistRepository.save(wishlist);

        return userMapper.convertToUserReadOnlyDTO(savedUser);
    }


    @Override
    @Transactional
    public UserReadOnlyDTO updateUser(Long userId, UserUpdateDTO userUpdateDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userUpdateDTO.getUsername());
            user.setPassword(userUpdateDTO.getPassword());
            // Update other user-related fields as needed

            User updatedUser = userRepository.save(user);
            return userMapper.convertToUserReadOnlyDTO(updatedUser);
        } else {
            throw new UserNotFoundException("No user found.");
        }
    }

    @Override
    public List<UserReadOnlyDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convertToUserReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }


    @Override
    @Transactional
    public UserReadOnlyDTO deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            return userMapper.convertToUserReadOnlyDTO(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public User getUserEntityById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities()) // Use the authorities from your User entity
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Override
    public User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = getJwtFromRequest(request);

        if (StringUtils.hasText(token)) {
            String username = jwtTokenProvider.extractUsername(token);
            if (StringUtils.hasText(username)) {
                User user = userRepository.findByUsername(username);
                if (user != null) {
                    return user;
                }
            }
        }
        return null;
    }
}



