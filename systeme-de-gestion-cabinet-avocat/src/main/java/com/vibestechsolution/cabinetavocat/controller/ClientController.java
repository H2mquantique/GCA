package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Affaire;
import com.vibestechsolution.cabinetavocat.entity.Client;
import com.vibestechsolution.cabinetavocat.entity.Dossier;
import com.vibestechsolution.cabinetavocat.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getall")
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(admin -> ResponseEntity.ok().body(admin))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{clientId}/affaires")
    public List<Affaire> getAffairesByClientId(@PathVariable Long clientId) {
        return clientRepository.findAffairesByClientId(clientId);
    }

    @GetMapping("/{clientId}/dossier")
    public ResponseEntity<Dossier> getDossierByClientId(@PathVariable Long clientId) {
        Dossier dossier = clientRepository.findDossierByClientId(clientId);
        if (dossier == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dossier);
    }

    @PostMapping("/save")
    public Client saveClient(@RequestBody Client c) {
        c.setPassword(passwordEncoder.encode(c.getPassword())); // Encodage du mot de passe
        return clientRepository.save(c);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setUsername(clientDetails.getUsername());
                    client.setLastname(clientDetails.getLastname());
                    client.setCin(clientDetails.getCin());
                    client.setAdresse(clientDetails.getAdresse());
                    client.setTelephone1(clientDetails.getTelephone1());
                    client.setTelephone2(clientDetails.getTelephone2());
                    client.setStatut(clientDetails.getStatut());

                    // Gestion des autorités
                    if (clientDetails.getAuthorities() != null) {
                        client.setAuthorities(clientDetails.getAuthoritiesAsString());
                    }

                    // Gestion du mot de passe (encoder si fourni)
                    if (clientDetails.getPassword() != null && !clientDetails.getPassword().isEmpty()) {
                        client.setPassword(passwordEncoder.encode(clientDetails.getPassword()));
                    }

                    Client updatedClient = clientRepository.save(client);
                    return ResponseEntity.ok().body(updatedClient);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
