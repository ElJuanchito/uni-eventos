package co.edu.uniquindio.uni_eventos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()  // Permitir acceso a todas las rutas sin autenticación
                )
                .csrf().disable()  // Desactivar CSRF si no lo necesitas
                .formLogin().disable();  // Desactivar el formulario de login

        return http.build();
    }
}
