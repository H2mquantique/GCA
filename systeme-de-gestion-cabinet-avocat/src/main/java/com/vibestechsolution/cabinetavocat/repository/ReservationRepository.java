package com.vibestechsolution.cabinetavocat.repository;

import com.vibestechsolution.cabinetavocat.entity.Reservation;
import com.vibestechsolution.cabinetavocat.entity.Client;
import com.vibestechsolution.cabinetavocat.entity.AdminAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClient(Client client);
    List<Reservation> findByAvailability(AdminAvailability availability);
}
