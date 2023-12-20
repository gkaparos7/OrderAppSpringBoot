package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.AuthenticationRequest;
import gr.aueb.cf4.orderapp.dto.AuthenticationResponse;
import gr.aueb.cf4.orderapp.security.JwtTokenProvider;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {

        // First authentication
        Authentication authenticationResult = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationResult);

        // Get the updated authentication from the context
        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();

        // Extract the username from the authenticated object
        String username = authenticated.getName();

        // Generate JWT token using the extracted username
        String jwt = jwtTokenProvider.generateToken(username);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}

