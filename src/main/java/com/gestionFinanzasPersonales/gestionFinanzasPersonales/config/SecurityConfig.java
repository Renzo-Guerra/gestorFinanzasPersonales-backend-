package com.gestionFinanzasPersonales.gestionFinanzasPersonales.config;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.JWT.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authRequest ->
                    authRequest
                            // Todos los endpoints que empiecen con auth pueden acceder
                            .requestMatchers("/api/v1/auth/**").permitAll()
                            // En los demas se valida el token
                            .anyRequest().authenticated()
                    )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
        // csrf (Crosside request forgery) es una proteccion que habilita por default springboot
        // es una medida de seguridad que se utiliza para agregar a las solicitudes POST una autenticacion
        // basada en un token csrf valido.
        // Lo deshabilitamos para que no nos lo pida cada vez que hagamos un POST
    }
}
