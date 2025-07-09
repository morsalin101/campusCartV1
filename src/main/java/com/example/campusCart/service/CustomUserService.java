package com.example.campusCart.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserService {
     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}