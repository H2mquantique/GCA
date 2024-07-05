package com.vibestechsolution.cabinetavocat.repository;


import com.vibestechsolution.cabinetavocat.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}