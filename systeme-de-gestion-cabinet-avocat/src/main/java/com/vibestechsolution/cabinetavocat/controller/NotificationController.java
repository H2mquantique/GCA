package com.vibestechsolution.cabinetavocat.controller;

import com.vibestechsolution.cabinetavocat.NotificationService;
import com.vibestechsolution.cabinetavocat.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifs")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notifyadmin")
    public String notifyAdmin(@RequestBody String message) {
        notificationService.addNotification(message);
        return "Notification envoyée à l'administrateur : " + message;
    }

    @GetMapping("/admin-notifications")
    public List<Notification> getAdminNotifications() {
        return notificationService.getNotifications();
    }
}
