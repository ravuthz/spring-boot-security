package com.tutorial.security.payloads.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(Long id, String token, String email, List<String> roles, String username) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.roles = roles;
        this.username = username;
    }
}
