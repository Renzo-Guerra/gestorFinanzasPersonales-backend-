package com.gestionFinanzasPersonales.gestionFinanzasPersonales.services;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.response.AuthResponse;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request.LoginRequest;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request.RegisterRequest;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.contrasenia()));
        Usuario user = usuarioService.getUserByEmail(request.email());
        return new AuthResponse(jwtService.getToken(user));
    }

    public AuthResponse register(RegisterRequest request) {
        Usuario user = this.usuarioService.createUser(request);
        return new AuthResponse(jwtService.getToken(user));
    }
}
