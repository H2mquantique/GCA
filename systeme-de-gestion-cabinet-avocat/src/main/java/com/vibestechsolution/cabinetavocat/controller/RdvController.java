package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.*;
import com.vibestechsolution.cabinetavocat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rdvs")
public class RdvController {
    @Autowired
    private RdvRepository rdvRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminAvailabilityRepository availabilityRepository;

    @Autowired
    @GetMapping("/getall")
    public List<Rdv> getAllDossiers() {
        return rdvRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Rdv> getRdvById(@PathVariable Long id) {
        return rdvRepository.findById(id)
                .map(rdv -> ResponseEntity.ok().body(rdv))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/findByDateRDV")
    public ResponseEntity<Rdv> getRDVByDateRDV(@RequestParam String dateRDV) {
        Optional<Rdv> rdv = rdvRepository.findByDateRDV(dateRDV);
        if (rdv.isPresent()) {
            return ResponseEntity.ok(rdv.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/save")
    public Rdv saveRdv(@RequestBody Rdv r) {

        return rdvRepository.save(r);
    }
    @PostMapping("/save/{client_id}/{admin_id}")
    public ResponseEntity<Rdv> addRDV(@PathVariable Long client_id, @PathVariable Long admin_id, @RequestBody Rdv rdv) {

        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        rdv.setClient(client);
        rdv.setAdmin(admin);
        Rdv savedRDV = rdvRepository.save(rdv);
        return ResponseEntity.ok(savedRDV);
    }

    @PostMapping("/saveByAvailability/{client_id}/{availability_id}")
    public ResponseEntity<Rdv> saveByAvailability(@PathVariable Long client_id, @PathVariable Long availability_id, @RequestBody Rdv rdv) {
        rdv.setStatus("PENDING"); // Default status
        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        AdminAvailability availability = availabilityRepository.findById(availability_id)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        Admin admin = availability.getAdmin();

        rdv.setClient(client);
        rdv.setAdmin(admin);
        rdv.setAvailability(availability);
        Rdv savedRDV = rdvRepository.save(rdv);
        return ResponseEntity.ok(savedRDV);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Rdv> updateRdv(@PathVariable Long id, @RequestBody Rdv rdvDetails) {
        return rdvRepository.findById(id)
                .map(rdv -> {
                    rdv.setDateRDV(rdvDetails.getDateRDV());
                    rdv.setDescription(rdvDetails.getDescription());



                    Rdv updatedRdv = rdvRepository.save(rdv);
                    return ResponseEntity.ok().body(updatedRdv);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{client_id}/{admin_id}")
    public ResponseEntity<Rdv> updateRDV(@PathVariable Long client_id, @PathVariable Long admin_id, @RequestBody Rdv rdvDetails) {

        // Retrieve RDV by client and admin IDs
        Rdv rdv = (Rdv) rdvRepository.findByClientIdAndAdminId(client_id, admin_id)
                .orElseThrow(() -> new RuntimeException("RDV not found"));

        // Ensure the client and admin are valid
        Client client = clientRepository.findById(client_id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // Update the RDV details
        rdv.setDateRDV(rdvDetails.getDateRDV());
        rdv.setDescription(rdvDetails.getDescription());

        // Save the updated RDV
        Rdv updatedRDV = rdvRepository.save(rdv);
        return ResponseEntity.ok(updatedRDV);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteRdv(@PathVariable Long id) {
        return rdvRepository.findById(id)
                .map(rdv -> {
                    rdvRepository.delete(rdv);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }




    //Acceptation et refus d'un rdv

    @PutMapping("/accept/{id}")
    public ResponseEntity<Rdv> acceptRdv(@PathVariable Long id) {
        Rdv rdv = rdvRepository.findById(id).orElseThrow(() -> new RuntimeException("Rdv not found"));
        rdv.setStatus("ACCEPTED");
        return ResponseEntity.ok(rdvRepository.save(rdv));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<Rdv> rejectRdv(@PathVariable Long id) {
        Rdv rdv = rdvRepository.findById(id).orElseThrow(() -> new RuntimeException("Rdv not found"));
        rdv.setStatus("REJECTED");
        return ResponseEntity.ok(rdvRepository.save(rdv));
    }
}
