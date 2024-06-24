package com.gestionFinanzasPersonales.gestionFinanzasPersonales.controllers;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> metodoPublico(){
        return ResponseEntity.status(HttpStatus.OK).body("Entro!");
    }
}
