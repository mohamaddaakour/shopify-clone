package com.shopify.controller;

import com.shopify.dto.LoginRequestDTO;
import com.shopify.dto.CurrentUserResponseDTO;
import com.shopify.dto.RegisterRequestDTO;
import com.shopify.dto.RegisterResponseDTO;
import com.shopify.entity.User;
import com.shopify.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestDTO request, HttpServletResponse response) {

        authService.login(request.getEmail(), request.getPassword(), response);

        return "Login successful (JWT stored in cookie)";
    }

    @GetMapping("/me")
    public CurrentUserResponseDTO me(@AuthenticationPrincipal User user) {
        return new CurrentUserResponseDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        authService.logout(response);
        return "Logged out";
    }
}
