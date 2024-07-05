package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.ServiceSelectionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/select")
public class ServiceSelectionController {

    @PostMapping("/select-service")
    public ResponseEntity<Object> selectService(@RequestBody ServiceSelectionRequest request) {
        // Logique pour sélectionner le service et retourner une réponse appropriée
        return ResponseEntity.ok().build();
    }
}
