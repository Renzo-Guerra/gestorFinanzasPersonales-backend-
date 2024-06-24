package com.gestionFinanzasPersonales.gestionFinanzasPersonales.controllers;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.response.AuthResponse;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.services.AuthService;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request.LoginRequest;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }
}
