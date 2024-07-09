package com.vibestechsolution.cabinetavocat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vibestechsolution.cabinetavocat.user.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "clients")
@JsonIgnoreProperties(ignoreUnknown = true)

public class Client extends User {

    private String firstname;


    private String cin;
    private String adresse;
    private String telephone1;
    private String telephone2;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnore
    private Dossier dossier;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Rdv> rdvs;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Honoraire> honoraires;

    // Constructors
    public Client() {
        super();
    }

    // Getters and Setters
    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public Set<Rdv> getRdvs() {
        return rdvs;
    }

    public void setRdvs(Set<Rdv> rdvs) {
        this.rdvs = rdvs;
    }

    public Set<Honoraire> getHonoraires() {
        return honoraires;
    }

    public void setHonoraires(Set<Honoraire> honoraires) {
        this.honoraires = honoraires;
    }
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

}
