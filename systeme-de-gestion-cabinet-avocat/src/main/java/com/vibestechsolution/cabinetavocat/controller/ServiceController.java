package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Client;
import com.vibestechsolution.cabinetavocat.entity.Rdv;
import com.vibestechsolution.cabinetavocat.entity.Service;
import com.vibestechsolution.cabinetavocat.repository.ClientRepository;
import com.vibestechsolution.cabinetavocat.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/getall")
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        return serviceRepository.findById(id)
                .map(service -> ResponseEntity.ok().body(service))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Service> saveService(@RequestBody Service service, @AuthenticationPrincipal UserDetails userDetails) {
        // Récupérer l'ID du client connecté à partir des informations d'authentification
        Long clientId = extractClientIdFromUserDetails(userDetails);

        // Récupérer le client à partir de l'ID (vous devrez probablement implémenter cette méthode dans votre service)
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Associer le client au service
        service.setClient(client);

        // Sauvegarder le service
        Service savedService = serviceRepository.save(service);
        return ResponseEntity.ok(savedService);
    }

    private Long extractClientIdFromUserDetails(UserDetails userDetails) {
        // Implémentez cette méthode pour extraire l'ID du client connecté à partir de UserDetails
        // Cela dépendra de votre méthode d'authentification (JWT, session, etc.)
        // Exemple fictif :
        // return myAuthService.extractClientId(userDetails);
        return null; // À remplacer par la logique réelle
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Service> updateRdv(@PathVariable Long id, @RequestBody Service serviceDetails) {
        return serviceRepository.findById(id)
                .map(service -> {
                    service.setNom(serviceDetails.getNom());

                    Service updatedService = serviceRepository.save(service);


                    return ResponseEntity.ok().body(updatedService);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable Long id) {
        return serviceRepository.findById(id)
                .map(service -> {
                    serviceRepository.delete(service);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
