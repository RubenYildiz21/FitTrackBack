package com.fittrack.fit_track.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fittrack.fit_track.model.User;
import com.fittrack.fit_track.repository.UserRepository;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        //TODO: process POST request

        if (userOptional.isPresent()){
            User user = userOptional.get();

            if(passwordEncoder.matches(password, user.getPassword())){
                return "Connexion succes";
            }else{
                return "Password wrong";
            }
        }else{
            return "User not found";
        }
    }
    
    
}
