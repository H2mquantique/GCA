package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Delais;
import com.vibestechsolution.cabinetavocat.entity.Rdv;
import com.vibestechsolution.cabinetavocat.repository.DelaisRepository;
import com.vibestechsolution.cabinetavocat.repository.RdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delais")
public class DelaisController {
    @Autowired
    private DelaisRepository delaisRepository;
    @GetMapping("/getall")
    public List<Delais> getAllDelais() {
        return delaisRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Delais> getDelaisById(@PathVariable Long id) {
        return delaisRepository.findById(id)
                .map(delais -> ResponseEntity.ok().body(delais))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/save")
    public Delais saveDelais(@RequestBody Delais d) {

        return delaisRepository.save(d);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Delais> updateDelais(@PathVariable Long id, @RequestBody Delais delaisDetails) {
        return delaisRepository.findById(id)
                .map(delais -> {
                    delais.setType(delaisDetails.getType());



                    Delais updatedDelais = delaisRepository.save(delais);
                    return ResponseEntity.ok().body(updatedDelais);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteDelais(@PathVariable Long id) {
        return delaisRepository.findById(id)
                .map(rdv -> {
                    delaisRepository.delete(rdv);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
