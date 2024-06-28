package com.vibestechsolution.cabinetavocat.repository;

import com.vibestechsolution.cabinetavocat.entity.Affaire;
import com.vibestechsolution.cabinetavocat.entity.Client;
import com.vibestechsolution.cabinetavocat.entity.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT a FROM Affaire a WHERE a.dossier.client.id = :clientId")
    List<Affaire> findAffairesByClientId(@Param("clientId") Long clientId);
    @Query("SELECT d FROM Dossier d JOIN d.client c WHERE c.id = :clientId")
    Dossier findDossierByClientId(@Param("clientId") Long clientId);
}
