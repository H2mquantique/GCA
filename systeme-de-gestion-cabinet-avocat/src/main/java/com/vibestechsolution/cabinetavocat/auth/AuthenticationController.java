package com.vibestechsolution.cabinetavocat.auth;

import com.vibestechsolution.cabinetavocat.entity.Admin;
import com.vibestechsolution.cabinetavocat.entity.Client;
import com.vibestechsolution.cabinetavocat.repository.AdminRepository;
import com.vibestechsolution.cabinetavocat.repository.ClientRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name="Authentification")
public class AuthenticationController {
    private final AuthenticationService service;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AuthenticationService authService;



    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        service.register(request);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/activate-account")
    public void confirm(
            @RequestParam String token
    ) throws MessagingException {
        service.activateAccount(token);
    }
    @PostMapping("/forget-password")
    public ResponseEntity<?> forgetPassword(@RequestParam String email) throws MessagingException {
        service.sendPasswordResetToken(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestParam String token,
            @RequestBody @Valid ResetPasswordRequest request
    ) {
        service.resetPassword(token, request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/register/client")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        try {
            authService.registerClient(client);
            return ResponseEntity.ok(client);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        try {
            authService.registerAdmin(admin);
            return ResponseEntity.ok(admin);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

}
