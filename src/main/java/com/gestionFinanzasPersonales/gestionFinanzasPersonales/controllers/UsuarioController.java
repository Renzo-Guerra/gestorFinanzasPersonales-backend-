package com.gestionFinanzasPersonales.gestionFinanzasPersonales.controllers;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request.CrearUsuarioDTO;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.entities.Usuario;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody CrearUsuarioDTO data) throws Exception {
        return new ResponseEntity<>(this.usuarioService.crearUsuario(data), HttpStatus.CREATED);
    }

}
