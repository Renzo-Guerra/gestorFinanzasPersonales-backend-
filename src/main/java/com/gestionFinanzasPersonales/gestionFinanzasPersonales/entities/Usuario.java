package com.gestionFinanzasPersonales.gestionFinanzasPersonales.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id_usuario;

    @Email
    @Length(max = 40)
    String email;

    @NotBlank
    @Length(min = 5, max = 255)
    String contrasenia;

    public Usuario(String email, String contrasenia){
        setEmail(email);
        setContrasenia(contrasenia);
    }
}
