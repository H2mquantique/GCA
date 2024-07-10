package com.vibestechsolution.cabinetavocat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vibestechsolution.cabinetavocat.role.Role;
import com.vibestechsolution.cabinetavocat.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "admins")
@JsonIgnoreProperties(value = { "affaires", "rdvs", "delais","audiences", "availabilities","roles"}, allowSetters = true, ignoreUnknown = true)

public class Admin extends User {



    @OneToMany(mappedBy = "admin")
    private Set<Rdv> rdvs;

    @OneToMany(mappedBy = "admin")
    private Set<Affaire> affaires;

    @OneToMany(mappedBy = "admin")
    private Set<Delais> delais;

    @OneToMany(mappedBy = "admin")
    private Set<Audience> audiences;
    @OneToMany(mappedBy = "admin")
    private Set<AdminAvailability> availabilities = new HashSet<>();



    // Constructors
    public Admin() {
        super();
    }

    // Constructor that initializes User fields


    // Getters and Setters



    public Set<Affaire> getAffaires() {
        return affaires;
    }

    public void setAffaires(Set<Affaire> affaires) {
        this.affaires = affaires;
    }

    public Set<Audience> getAudiences() {
        return audiences;
    }

    public void setAudiences(Set<Audience> audiences) {
        this.audiences = audiences;
    }
    public Set<Rdv> getRdvs() {
        return rdvs;
    }

    public void setRdvs(Set<Rdv> rdvs) {
        this.rdvs = rdvs;
    }

    public Set<Delais> getDelais() {
        return delais;
    }

    public void setDelais(Set<Delais> delais) {
        this.delais = delais;
    }



    public Set<AdminAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<AdminAvailability> availabilities) {
        this.availabilities = availabilities;
    }

}
