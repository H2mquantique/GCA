/*package com.vibestechsolution.cabinetavocat;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ServiceSelectionListener {

    private final SimpMessagingTemplate messagingTemplate;

    public ServiceSelectionListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleServiceSelectionEvent(ServiceSelectionEvent event) {
        // Envoyer la notification via WebSocket
        messagingTemplate.convertAndSend("/topic/service-selection", "Service sélectionné : " + event.getServiceName());
    }
}*/


