package com.fittrack.fit_track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Autoriser Swagger UI sans authentification
        http
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                                .anyRequest().authenticated() // Authentification pour les autres endpoints
                )
                .formLogin(login -> login  // Formulaire de login
                        .permitAll())
                .logout(logout -> logout
                        .permitAll());

        return http.build();
    }
}