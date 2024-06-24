package com.gestionFinanzasPersonales.gestionFinanzasPersonales.services;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request.RegisterRequest;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.Exceptions.Exceptions.EmailYaExisteException;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.entities.Rol;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.entities.Usuario;
import com.gestionFinanzasPersonales.gestionFinanzasPersonales.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario getUserByEmail(String email){
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);
        if(usuario.isEmpty()){ throw new UsernameNotFoundException("El mail ingresado no se encuentra registrado!"); }

        return usuario.get();
    }

    private boolean emailAlreadyRegistered(String email){
        return this.usuarioRepository.findByEmail(email).isPresent();
    }

    public Usuario createUser(RegisterRequest request) {
        if(emailAlreadyRegistered(request.email())){ throw new EmailYaExisteException("El mail ingresado ya se encuentra registrado"); }

        Usuario user = Usuario.builder()
                .email(request.email())
                .contrasenia(passwordEncoder.encode(request.contrasenia()))
                .rol(Rol.USER)
                .build();
        return this.usuarioRepository.save(user);
    }
}
