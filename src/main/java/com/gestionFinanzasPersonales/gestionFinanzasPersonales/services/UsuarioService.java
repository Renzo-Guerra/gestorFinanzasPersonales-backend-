package com.gestionFinanzasPersonales.gestionFinanzasPersonales.services;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request.CrearUsuarioDTO;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.entities.Usuario;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(CrearUsuarioDTO data) throws Exception {
        Optional<Usuario> user = this.usuarioRepository.findByEmail(data.email());
        if(user.isPresent()){ throw new Exception("Ya existe un usuario con ese email!"); }

        return this.usuarioRepository.save(new Usuario(data.email(), data.contrasenia()));
    }
}
