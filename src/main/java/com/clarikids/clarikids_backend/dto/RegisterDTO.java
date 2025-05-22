package com.clarikids.clarikids_backend.dto;

public class RegisterDTO {
    private String username;
    private String password;
    private String role;

    // Constructor vacío (importante para que funcione el mapeo)
    public RegisterDTO() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}