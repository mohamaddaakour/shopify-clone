package com.shopify.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopify.repository.UserRepository;
import com.shopify.security.CookieService;
import com.shopify.security.JwtService;
import com.shopify.dto.RegisterRequestDTO;
import com.shopify.dto.RegisterResponseDTO;
import com.shopify.entity.User;
import com.shopify.enums.RoleEnum;

@Service
@RequiredArgsConstructor
public class AuthService {

    // dependency injection
    private final JwtService jwtService;
    private final CookieService cookieService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegisterResponseDTO register(RegisterRequestDTO request) {

        // check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // create user
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.CUSTOMER)
                .build();

        User savedUser = userRepository.save(user);

        return new RegisterResponseDTO(savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole(), savedUser.getCreated_at());
    }

    public void login(String email, String password, HttpServletResponse response) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        // to check if the password of this user is the same in database
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // we generate a token for this user
        String token = jwtService.generateToken(email);

        // we store this token in a cookie in the user's browser
        cookieService.addJwtCookie(response, token);
    }

    // function to logout and clear the cookie
    public void logout(HttpServletResponse response) {
        cookieService.clearJwtCookie(response);
    }
}