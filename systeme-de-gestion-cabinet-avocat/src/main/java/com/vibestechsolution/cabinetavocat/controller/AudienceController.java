package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Admin;
import com.vibestechsolution.cabinetavocat.entity.Affaire;
import com.vibestechsolution.cabinetavocat.entity.Audience;
import com.vibestechsolution.cabinetavocat.repository.AdminRepository;
import com.vibestechsolution.cabinetavocat.repository.AffaireRepository;
import com.vibestechsolution.cabinetavocat.repository.AudienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audiences")
public class AudienceController {
    @Autowired
    private AudienceRepository audienceRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AffaireRepository affaireRepository;
    @GetMapping("/getall")
    public List<Audience> getAllAudiences() {
        return audienceRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Audience> getAudienceById(@PathVariable Long id) {
        return audienceRepository.findById(id)
                .map(admin -> ResponseEntity.ok().body(admin))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveAudience(@RequestBody Audience audience) {
        try {
            // Vérifier si l'ID de l'admin existe
            if (!adminRepository.existsById(audience.getAdmin().getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'administrateur avec l'ID spécifié n'existe pas.");
            }

            // Vérifier si l'ID de l'affaire existe
            if (!affaireRepository.existsById(audience.getAffaire().getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'affaire avec l'ID spécifié n'existe pas.");
            }

            // Enregistrer l'audience dans la base de données
            Audience savedAudience = audienceRepository.save(audience);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAudience);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'audience : " + e.getMessage());
        }
    }
    @PostMapping("/save/{admin_id}/{affaire_id}")
    public ResponseEntity<Audience> addAudience(@PathVariable Long admin_id, @PathVariable Long affaire_id,@RequestBody Audience audience) {

        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        Affaire affaire = affaireRepository.findById(affaire_id)
                .orElseThrow(() -> new RuntimeException("Affaire not found"));

        audience.setAdmin(admin);
        audience.setAffaire(affaire);
        Audience savedAudience = audienceRepository.save(audience);
        return ResponseEntity.ok(savedAudience);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Audience> updateAudience(@PathVariable Long id, @RequestBody Audience audienceDetails) {
        return audienceRepository.findById(id)
                .map(audience -> {
                    audience.setDateAudience(audienceDetails.getDateAudience());
                    audience.setDescription(audienceDetails.getDescription());


                    Audience updatedAudience = audienceRepository.save(audience);
                    return ResponseEntity.ok().body(updatedAudience);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAudience(@PathVariable Long id) {
        return audienceRepository.findById(id)
                .map(audience -> {
                    audienceRepository.delete(audience);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
