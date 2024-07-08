package com.vibestechsolution.cabinetavocat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@JsonIgnoreProperties(value = { "client", "availability" }, allowSetters = true)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "availability_id")
    private AdminAvailability availability;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

    private LocalDateTime reservationTime;
    private String status = "pending"; // Ajout du champ status avec la valeur par défaut "pending"


    // Constructors, getters, and setters
    public Reservation() {}

    public Reservation(Client client, AdminAvailability availability, LocalDateTime reservationTime) {
        this.client = client;
        this.availability = availability;
        this.reservationTime = reservationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public AdminAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(AdminAvailability availability) {
        this.availability = availability;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
