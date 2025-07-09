package com.example.campusCart.service;
import com.example.campusCart.model.UserModel;

public interface UserService {
    UserModel findByUsername(String username);
}
