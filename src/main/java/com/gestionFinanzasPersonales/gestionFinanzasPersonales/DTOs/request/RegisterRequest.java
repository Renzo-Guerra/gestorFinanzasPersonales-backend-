package com.gestionFinanzasPersonales.gestionFinanzasPersonales.DTOs.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RegisterRequest (@Email
                               @NotBlank
                               @Length(max = 40)
                               String email,
                               @NotBlank
                               @Length(min = 5, max = 255)
                               String contrasenia){}
