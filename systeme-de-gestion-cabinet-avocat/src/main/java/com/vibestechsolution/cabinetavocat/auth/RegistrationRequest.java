package com.vibestechsolution.cabinetavocat.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message="Firstname is mandatory")
    @NotBlank(message="Firstname is mandatory")
    private String username;
    @NotEmpty(message="Lastname is mandatory")
    @NotBlank(message="Lastname is mandatory")
    private String lastname;
    @JsonProperty("email")

    @Email(message="Email is not formatted")
    @NotEmpty(message="Email is mandatory")
    @NotBlank(message="Email is mandatory")
    private String email;
    @Size(min=8, message = "Password should be 8 characters long minimum")
    @NotEmpty(message="Password is mandatory")
    @NotBlank(message="Password is mandatory")
    @JsonProperty("password")
    private String password;

    public RegistrationRequest() {
        // Constructeur par défaut nécessaire pour la désérialisation JSON
    }

    // Autres constructeurs, getters et setters

    @JsonCreator
    public RegistrationRequest(@JsonProperty("username") String username,
                               @JsonProperty("lastname") String lastname,
                               @JsonProperty("email") String email,
                               @JsonProperty("password") String password) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

}
