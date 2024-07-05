/*package com.vibestechsolution.cabinetavocat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/send/message") // Mapping pour recevoir les messages du client
    @SendTo("/topic/messages") // Mapping pour envoyer les messages à tous les abonnés sur "/topic/messages"
    public String sendMessage(String message) {
        return "Received: " + message;
    }
}
*/