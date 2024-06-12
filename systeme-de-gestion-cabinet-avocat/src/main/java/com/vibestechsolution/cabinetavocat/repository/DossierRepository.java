package com.vibestechsolution.cabinetavocat.repository;

import com.vibestechsolution.cabinetavocat.entity.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierRepository extends JpaRepository<Dossier,Long> {
    Dossier getDossierByClientId(Long clientId);
}
