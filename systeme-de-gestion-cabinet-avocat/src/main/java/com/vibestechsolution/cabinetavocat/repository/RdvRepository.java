package com.vibestechsolution.cabinetavocat.repository;

import com.vibestechsolution.cabinetavocat.entity.Rdv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RdvRepository extends JpaRepository<Rdv,Long> {
    Optional<Object> findByClientIdAndAdminId(Long clientId, Long adminId);
    Optional<Rdv> findByDateRDV(String dateRDV);
    List<Rdv> findByStatus(String status);


}
