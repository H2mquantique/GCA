package com.vibestechsolution.cabinetavocat.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
    private Long id;
    private String email;
    private String username;
    private String lastname;
}