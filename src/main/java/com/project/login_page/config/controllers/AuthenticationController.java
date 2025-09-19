package com.project.login_page.config.controllers;

import com.project.login_page.domain.User;
import com.project.login_page.dto.LoginRequestDTO;
import com.project.login_page.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok("Usuário criado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginRequestDTO){
        String username = userService.loginUser(loginRequestDTO);
        return ResponseEntity.ok("Login efetuado com sucesso!");
    }

}
