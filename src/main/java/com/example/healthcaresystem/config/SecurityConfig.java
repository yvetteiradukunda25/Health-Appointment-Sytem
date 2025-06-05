package com.example.healthcaresystem.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/index.html", "/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
}