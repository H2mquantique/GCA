/*package com.vibestechsolution.cabinetavocat.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Permet de définir un broker simple pour les destinations préfixées "/topic"
        config.setApplicationDestinationPrefixes("/app"); // Définit le préfixe des destinations de l'application
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Définit l'endpoint pour les connexions WebSocket
                .setAllowedOrigins("http://localhost:4200") // Autorise les connexions depuis ce domaine (Angular)
                .withSockJS(); // Active SockJS pour le support des navigateurs qui ne prennent pas en charge WebSocket natif
    }
}*/
