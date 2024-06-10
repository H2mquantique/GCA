package com.vibestechsolution.cabinetavocat.repository;

import com.vibestechsolution.cabinetavocat.entity.Audience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AudienceRepository extends JpaRepository<Audience, Long> {
    Optional<Audience> findByDateAudience (String dateAudience);
}
