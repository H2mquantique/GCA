package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Intervenant;
import com.vibestechsolution.cabinetavocat.entity.Rdv;
import com.vibestechsolution.cabinetavocat.repository.IntervenantRepsoitory;
import com.vibestechsolution.cabinetavocat.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intervenants")
public class IntervenantController {

    @Autowired
    private IntervenantRepsoitory intervenantRepository;
    @GetMapping("/getall")
    public List<Intervenant> getAllIntervs() {
        return intervenantRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Intervenant> getIntervenantById(@PathVariable Long id) {
        return intervenantRepository.findById(id)
                .map(intervenant -> ResponseEntity.ok().body(intervenant))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/save")
    public Intervenant saveRdv(@RequestBody Intervenant i) {

        return intervenantRepository.save(i);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Intervenant> updateIntervenant(@PathVariable Long id, @RequestBody Intervenant intervenantDetails) {
        return intervenantRepository.findById(id)
                .map(intervenant -> {
                    intervenant.setCategory(intervenantDetails.getCategory());
                    intervenant.setNom(intervenantDetails.getNom());
                    intervenant.setPrenom(intervenantDetails.getPrenom());
                    intervenant.setAdresse(intervenantDetails.getAdresse());




                    Intervenant updatedIntervenant = intervenantRepository.save(intervenant);
                    return ResponseEntity.ok().body(updatedIntervenant);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRdv(@PathVariable Long id) {
        return intervenantRepository.findById(id)
                .map(intervenant -> {
                    intervenantRepository.delete(intervenant);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
