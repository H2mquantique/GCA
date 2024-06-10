package com.vibestechsolution.cabinetavocat.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "dossiers")
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroDossier;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "dossier")
    private Set<Affaire> affaires;

    // Constructors
    public Dossier() {}

    public Dossier(String numeroDossier, Client client) {
        this.numeroDossier = numeroDossier;
        this.client = client;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDossier() {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Affaire> getAffaires() {
        return affaires;
    }

    public void setAffaires(Set<Affaire> affaires) {
        this.affaires = affaires;
    }
}
