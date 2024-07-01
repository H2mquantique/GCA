package com.vibestechsolution.cabinetavocat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "availabilities")
@JsonIgnoreProperties(value = { "admin" }, allowSetters = true)

public class AdminAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String startTime;
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    // Constructors, getters, setters
    public AdminAvailability() {}

    public AdminAvailability(String date, String startTime, String endTime, Admin admin) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
