package com.vibestechsolution.cabinetavocat.repository;

import com.vibestechsolution.cabinetavocat.entity.Audience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AudienceRepository extends JpaRepository<Audience, Long> {
    List<Audience> findByDateAudience (String dateAudience);

    List<Audience> findByAffaireId(Long affaireId);
}
