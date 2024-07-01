package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.entity.Reservation;
import com.vibestechsolution.cabinetavocat.entity.Client;
import com.vibestechsolution.cabinetavocat.entity.AdminAvailability;
import com.vibestechsolution.cabinetavocat.repository.ReservationRepository;
import com.vibestechsolution.cabinetavocat.repository.ClientRepository;
import com.vibestechsolution.cabinetavocat.repository.AdminAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminAvailabilityRepository adminAvailabilityRepository;

    // Créer une réservation
    @PostMapping("/create/{client_id}/{availability_id}")
    public ResponseEntity<Reservation> createReservation(
            @PathVariable("client_id") Long clientId,
            @PathVariable("availability_id") Long availabilityId,
            @RequestBody Reservation reservationDetails) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        AdminAvailability availability = adminAvailabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new RuntimeException("Availability not found"));

        Reservation reservation = new Reservation(client, availability, reservationDetails.getReservationTime());
        Reservation savedReservation = reservationRepository.save(reservation);

        return ResponseEntity.ok(savedReservation);
    }

    // Récupérer toutes les réservations
    @GetMapping("/getall")
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Récupérer une réservation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return ResponseEntity.ok(reservation);
    }

    // Récupérer les réservations par client
    @GetMapping("/by-client/{clientId}")
    public ResponseEntity<List<Reservation>> getReservationsByClient(@PathVariable Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        List<Reservation> reservations = reservationRepository.findByClient(client);
        return ResponseEntity.ok(reservations);
    }

    // Récupérer les réservations par disponibilité d'admin
    @GetMapping("/by-availability/{availabilityId}")
    public ResponseEntity<List<Reservation>> getReservationsByAvailability(@PathVariable Long availabilityId) {
        AdminAvailability availability = adminAvailabilityRepository.findById(availabilityId)
                .orElseThrow(() -> new RuntimeException("Availability not found"));
        List<Reservation> reservations = reservationRepository.findByAvailability(availability);
        return ResponseEntity.ok(reservations);
    }

    // Mettre à jour une réservation
    @PutMapping("/update/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setClient(reservationDetails.getClient());
        reservation.setAvailability(reservationDetails.getAvailability());
        reservation.setReservationTime(reservationDetails.getReservationTime());

        Reservation updatedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    // Supprimer une réservation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservationRepository.delete(reservation);
        return ResponseEntity.noContent().build();
    }
}
