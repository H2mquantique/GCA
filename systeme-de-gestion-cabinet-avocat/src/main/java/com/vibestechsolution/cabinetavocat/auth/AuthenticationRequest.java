package com.vibestechsolution.cabinetavocat.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

    @JsonProperty("email")
    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    private String email;

    @JsonProperty("password")
    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;

    public AuthenticationRequest() {
        // Constructeur par défaut nécessaire pour la désérialisation JSON
    }

    @JsonCreator
    public AuthenticationRequest(@JsonProperty("email") String email,
                                 @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}
