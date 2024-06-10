package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Admin;
import com.vibestechsolution.cabinetavocat.entity.Affaire;
import com.vibestechsolution.cabinetavocat.repository.AdminRepository;
import com.vibestechsolution.cabinetavocat.repository.AffaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/affaires")
public class AffaireController {
    @Autowired
    private AffaireRepository affaireRepository;
    @GetMapping("/getall")
    public List<Affaire> getAllAffaires() {
        return affaireRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Affaire> getAffaireById(@PathVariable Long id) {
        return affaireRepository.findById(id)
                .map(admin -> ResponseEntity.ok().body(admin))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/save")
    public Affaire saveAffaire(@RequestBody Affaire a) {

        return affaireRepository.save(a);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Affaire> updateAffaire(@PathVariable Long id, @RequestBody Affaire affaireDetails) {
        return affaireRepository.findById(id)
                .map(affaire -> {
                    affaire.setNumeroAffaire(affaireDetails.getNumeroAffaire());
                    affaire.setNatureAffaire(affaireDetails.getNatureAffaire());
                    affaire.setDateAudience(affaireDetails.getDateAudience());
                    affaire.setAboutissement(affaireDetails.getAboutissement());

                    Affaire updatedAffaire = affaireRepository.save(affaire);
                    return ResponseEntity.ok().body(updatedAffaire);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAffaire(@PathVariable Long id) {
        return affaireRepository.findById(id)
                .map(affaire -> {
                    affaireRepository.delete(affaire);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
