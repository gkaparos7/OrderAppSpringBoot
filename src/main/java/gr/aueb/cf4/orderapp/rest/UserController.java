package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.UserCreateDTO;
import gr.aueb.cf4.orderapp.dto.UserReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.UserUpdateDTO;
import gr.aueb.cf4.orderapp.model.User;
import gr.aueb.cf4.orderapp.service.IUserService;
import gr.aueb.cf4.orderapp.service.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, IUserService userService, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Register a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserCreateDTO userCreateDTO) {
        // Log the received data
        System.out.println("Received UserCreateDTO: " + userCreateDTO.toString());

        // Encode the password before saving it
        String encodedPassword = passwordEncoder.encode(userCreateDTO.getPassword());

        // Set the encoded password to the UserCreateDTO
        userCreateDTO.setPassword(encodedPassword);

        // Call the userService to create the user
        UserReadOnlyDTO registeredUser = userService.createUser(userCreateDTO);

        if (registeredUser != null) {
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User registration failed", HttpStatus.BAD_REQUEST);
        }
    }


    @Operation(summary = "Get all Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserReadOnlyDTO.class)))})})
    @GetMapping
    public ResponseEntity<List<UserReadOnlyDTO>> getAllUsers() {
        List<UserReadOnlyDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Get a User by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping("/{userId}")
    public ResponseEntity<UserReadOnlyDTO> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<>(userMapper.convertToUserReadOnlyDTO(user.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update a User by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @PutMapping("/{userId}")
    public ResponseEntity<UserReadOnlyDTO> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateDTO userUpdateDTO) {
        UserReadOnlyDTO updatedUser = userService.updateUser(userId, userUpdateDTO);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a User by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserReadOnlyDTO> deleteUser(@PathVariable Long userId) {
        UserReadOnlyDTO deletedUser = userService.deleteUser(userId);
        if (deletedUser != null) {
            return new ResponseEntity<>(deletedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


