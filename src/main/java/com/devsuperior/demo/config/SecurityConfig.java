package com.devsuperior.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder getPasswordEncoder(){ //Interface do BCrypt
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); //desabilitar csrf
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); //Configura permissão para todos os endpoints
        return http.build();
    }


}
