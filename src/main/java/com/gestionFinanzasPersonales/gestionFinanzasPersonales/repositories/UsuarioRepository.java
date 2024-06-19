package com.gestionFinanzasPersonales.gestionFinanzasPersonales.repositories;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("""
            SELECT u
            FROM Usuario u
            WHERE u.email = :email
            """)
    Optional<Usuario> findByEmail(String email);
}
