package com.vibestechsolution.cabinetavocat.controller;


import com.vibestechsolution.cabinetavocat.entity.*;
import com.vibestechsolution.cabinetavocat.repository.AffaireRepository;
import com.vibestechsolution.cabinetavocat.repository.ClientRepository;
import com.vibestechsolution.cabinetavocat.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/dossiers")
public class DossierController {
    @Autowired
    private DossierRepository dossierRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AffaireRepository affaireRepository;
    @GetMapping("/getall")
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Dossier> getDossierById(@PathVariable Long id) {
        return dossierRepository.findById(id)
                .map(dossier -> ResponseEntity.ok().body(dossier))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/getBy/{clientId}")
    public ResponseEntity<Dossier> getDossierByClientId(@PathVariable Long clientId) {
        Dossier dossier = dossierRepository.getDossierByClientId(clientId);
        if (dossier != null) {
            return ResponseEntity.ok(dossier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public Dossier saveDossier(@RequestBody Dossier d) {

        return dossierRepository.save(d);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Dossier> updateDossier(@PathVariable Long id, @RequestBody Dossier dossierDetails) {
        return dossierRepository.findById(id)
                .map(dossier -> {
                    dossier.setNumeroDossier(dossierDetails.getNumeroDossier());


                    Dossier updatedDossier = dossierRepository.save(dossier);
                    return ResponseEntity.ok().body(updatedDossier);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDossier(@PathVariable Long id) {
        return dossierRepository.findById(id)
                .map(dossier -> {
                    dossierRepository.delete(dossier);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/save/{client_id}/{affaire_id}")
    public ResponseEntity<Dossier> addDossier(@PathVariable Long client_id, @PathVariable Long affaire_id, @RequestBody Dossier dossier) {

        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        Affaire affaire = affaireRepository.findById(affaire_id)
                .orElseThrow(() -> new RuntimeException("Affaire not found"));

        dossier.setClient(client);
        dossier.setAffaires((Set<Affaire>) affaire);
        Dossier savedDossier = dossierRepository.save(dossier);
        return ResponseEntity.ok(savedDossier);
    }
}
