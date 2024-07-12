package com.vibestechsolution.cabinetavocat.entity;

import jakarta.persistence.*;

@Entity
@Table(name="honoraires")
public class Honoraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nature_reglement;
    private int tranche_paye;
    private int reste;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "affaire_id", nullable = false)
    private Affaire affaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNature_reglement() {
        return nature_reglement;
    }

    public void setNature_reglement(String nature_reglement) {
        this.nature_reglement = nature_reglement;
    }

    public int getTranche_paye() {
        return tranche_paye;
    }

    public void setTranche_paye(int tranche_paye) {
        this.tranche_paye = tranche_paye;
    }

    public int getReste() {
        return reste;
    }

    public void setReste(int reste) {
        this.reste = reste;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Affaire getAffaire() {
        return affaire;
    }

    public void setAffaire(Affaire affaire) {
        this.affaire = affaire;
    }
}
