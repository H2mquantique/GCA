package com.vibestechsolution.cabinetavocat.repository;

import com.vibestechsolution.cabinetavocat.entity.Affaire;
import com.vibestechsolution.cabinetavocat.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AffaireRepository extends JpaRepository<Affaire,Long> {
    Optional<Affaire> findByNumeroAffaire(String numeroAffaire);
    Optional<Affaire> findByDateAudience (String dateAudience);
}
