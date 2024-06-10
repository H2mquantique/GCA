package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Rdv;
import com.vibestechsolution.cabinetavocat.entity.Service;
import com.vibestechsolution.cabinetavocat.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceRepository serviceRepository;
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
    public Service saveRdv(@RequestBody Service s) {

        return serviceRepository.save(s);
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
