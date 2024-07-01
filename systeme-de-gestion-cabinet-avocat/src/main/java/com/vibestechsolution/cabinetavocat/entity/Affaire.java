package com.vibestechsolution.cabinetavocat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name = "affaires")
@JsonIgnoreProperties(value = { "admin", "dossier", "audiences","intervenantParAffaire" }, allowSetters = true)

public class Affaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroAffaire;
    private String natureAffaire;
    private String dateAudience;
    private String aboutissement;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "dossier_id", nullable = true)
    private Dossier dossier;


    @OneToMany(mappedBy = "affaire")
    private Set<Audience> audiences;
    @ManyToMany
    @JoinTable(
            name = "intervenant_par_affaire",
            joinColumns = @JoinColumn(name = "affaire_id"),
            inverseJoinColumns = @JoinColumn(name = "intervenant_id"))
    Set<Intervenant> intervenantParAffaire;


    // Constructors
    public Affaire() {}

    public Affaire(String numeroAffaire, String natureAffaire, String dateAudience, String aboutissement, Admin admin, Dossier dossier) {
        this.numeroAffaire = numeroAffaire;
        this.natureAffaire = natureAffaire;
        this.dateAudience = dateAudience;
        this.aboutissement = aboutissement;
        this.admin = admin;
        this.dossier = dossier;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroAffaire() {
        return numeroAffaire;
    }

    public void setNumeroAffaire(String numeroAffaire) {
        this.numeroAffaire = numeroAffaire;
    }

    public String getNatureAffaire() {
        return natureAffaire;
    }

    public void setNatureAffaire(String natureAffaire) {
        this.natureAffaire = natureAffaire;
    }

    public String getDateAudience() {
        return dateAudience;
    }

    public void setDateAudience(String dateAudience) {
        this.dateAudience = dateAudience;
    }

    public String getAboutissement() {
        return aboutissement;
    }

    public void setAboutissement(String aboutissement) {
        this.aboutissement = aboutissement;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public Set<Audience> getAudiences() {
        return audiences;
    }

    public void setAudiences(Set<Audience> audiences) {
        this.audiences = audiences;
    }
}
