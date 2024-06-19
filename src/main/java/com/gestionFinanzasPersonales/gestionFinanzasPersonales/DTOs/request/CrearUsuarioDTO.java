package com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CrearUsuarioDTO(@Email
                              @Length(max = 40)
                              String email,
                              @NotBlank
                              @Length(min = 5, max = 255)
                              String contrasenia) {}
