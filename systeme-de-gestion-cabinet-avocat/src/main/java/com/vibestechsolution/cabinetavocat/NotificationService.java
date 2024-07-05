package com.vibestechsolution.cabinetavocat;

import com.vibestechsolution.cabinetavocat.entity.Notification;
import com.vibestechsolution.cabinetavocat.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Notification saveNotification(String message) {
        Notification notification = new Notification(message, LocalDateTime.now());
        return notificationRepository.save(notification);
    }
    private final List<Notification> notifications = new ArrayList<>();

    public void addNotification(String message) {
        notifications.add(new Notification(message, LocalDateTime.now()));
    }

    public List<Notification> getNotifications() {
        return notifications;
    }
}
