package com.clarikids.clarikids_backend.controller;

import com.clarikids.clarikids_backend.model.User;
import com.clarikids.clarikids_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        String token = authService.login(username, password);
        String role = authService.getRoleByUsername(username);

        return Map.of(
            "token", token, 
            "role", role
        );
    }

    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String role = body.get("role");
        return authService.register(username, password, role);
    }
}