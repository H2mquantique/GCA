package com.vibestechsolution.cabinetavocat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rdvs")
public class Rdv{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateRDV;
    private String description;
    private String status; // "PENDING", "ACCEPTED", "REJECTED"


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @ManyToOne
    @JoinColumn(name = "availability_id")
    private AdminAvailability availability;


    // Constructors
    public Rdv() {}

    public Rdv(String dateRDV, String description, String status, Client client, Admin admin, AdminAvailability availability) {
        this.dateRDV = dateRDV;
        this.description = description;
        this.client = client;
        this.admin = admin;
        this.availability = availability;
        this.status=status;

    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(String dateRDV) {
        this.dateRDV = dateRDV;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public AdminAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(AdminAvailability availability) {
        this.availability = availability;
    }

}

