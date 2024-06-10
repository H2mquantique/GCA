package com.vibestechsolution.cabinetavocat.repository;

import com.vibestechsolution.cabinetavocat.entity.Admin;
import com.vibestechsolution.cabinetavocat.entity.AdminAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAvailabilityRepository extends JpaRepository<AdminAvailability, Long> {
    List<AdminAvailability> findByAdmin(Admin admin);
}
