package com.example.campusCart.config;

import com.example.campusCart.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserService customUserService;

    public SecurityConfig(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }
 
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return customUserService.loadUserByUsername(username);
            }
        }; // Replace with your own implementation
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
http
    //.csrf(csrf -> csrf.disable())  // Optional
    .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/",                         // homepage
                "/login", "/register",       // public auth pages
                "/assets/**",                // ✅ assets folder (css, js, images)
                "/static/**",                // ✅ static fallback (if accessed directly)
                "/css/**", "/js/**", "/images/**", "/fonts/**", "/favicon.ico","/libs/**" // ✅ other static resources
            ).permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/super-admin/**").hasRole("SUPER_ADMIN")
            .requestMatchers("/user/**").hasRole("USER")
            .anyRequest().authenticated()
    )
    .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard/index", true)
            .permitAll()
    )
    .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
    );

        return http.build();
    }

   
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        return new ProviderManager(List.of(new CustomAuthenticationProvider(userDetailsService, passwordEncoder())));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

