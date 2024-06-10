package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Honoraire;
import com.vibestechsolution.cabinetavocat.entity.Rdv;
import com.vibestechsolution.cabinetavocat.repository.HonoraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/honoraires")
public class HonoraireControler {
    @Autowired
    private HonoraireRepository honoraireRepository;
    @GetMapping("/getall")
    public List<Honoraire> getAllHonoraires() {
        return honoraireRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Honoraire> getHonoraireById(@PathVariable Long id) {
        return honoraireRepository.findById(id)
                .map(rdv -> ResponseEntity.ok().body(rdv))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/save")
    public Honoraire saveHonoraire(@RequestBody Honoraire h) {

        return honoraireRepository.save(h);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Honoraire> updateRdv(@PathVariable Long id, @RequestBody Honoraire honoraireDetails) {
        return honoraireRepository.findById(id)
                .map(honoraire -> {
                    honoraire.setNature_reglement(honoraireDetails.getNature_reglement());
                    honoraire.setTranche_paye(honoraireDetails.getTranche_paye());
                    honoraire.setReste(honoraireDetails.getReste());




                    Honoraire updatedHonoraire = honoraireRepository.save(honoraire);
                    return ResponseEntity.ok().body(updatedHonoraire);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteHonoraire(@PathVariable Long id) {
        return honoraireRepository.findById(id)
                .map(honoraire -> {
                    honoraireRepository.delete(honoraire);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
