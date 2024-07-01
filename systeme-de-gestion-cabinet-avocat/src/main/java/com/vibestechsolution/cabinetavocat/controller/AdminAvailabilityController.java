package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.AdminAvailability;
import com.vibestechsolution.cabinetavocat.entity.Admin;
import com.vibestechsolution.cabinetavocat.repository.AdminAvailabilityRepository;
import com.vibestechsolution.cabinetavocat.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-availabilities")
public class AdminAvailabilityController {

    @Autowired
    private AdminAvailabilityRepository adminAvailabilityRepository;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/create/{adminId}")
    public ResponseEntity<AdminAvailability> createAvailability(@PathVariable Long adminId,
                                                                @RequestBody AdminAvailability adminAvailability) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        adminAvailability.setAdmin(admin);
        AdminAvailability savedAvailability = adminAvailabilityRepository.save(adminAvailability);
        return ResponseEntity.ok(savedAvailability);
    }


    @GetMapping("/getall")
    public List<AdminAvailability> getAllDispo() {
        return adminAvailabilityRepository.findAll();
    }

    @GetMapping("/getByAdmin/{admin_id}")
    public ResponseEntity<List<AdminAvailability>> getAvailabilitiesByAdmin(@PathVariable Long admin_id) {
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return ResponseEntity.ok(adminAvailabilityRepository.findByAdmin(admin));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminAvailability> updateAvailability(@PathVariable Long id, @RequestBody AdminAvailability availabilityDetails) {
        AdminAvailability availability = adminAvailabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        availability.setDate(availabilityDetails.getDate());
        availability.setStartTime(availabilityDetails.getStartTime());
        availability.setEndTime(availabilityDetails.getEndTime());

        AdminAvailability updatedAvailability = adminAvailabilityRepository.save(availability);
        return ResponseEntity.ok(updatedAvailability);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        AdminAvailability availability = adminAvailabilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        adminAvailabilityRepository.delete(availability);
        return ResponseEntity.noContent().build();
    }
}
