package com.vibestechsolution.cabinetavocat;

import org.springframework.context.ApplicationEvent;

public class ServiceSelectionEvent extends ApplicationEvent {
    private final Long userId;
    private final String serviceName;

    public ServiceSelectionEvent(Object source, Long userId, String serviceName) {
        super(source);
        this.userId = userId;
        this.serviceName = serviceName;
    }

    // Getters
    public Long getUserId() {
        return userId;
    }

    public String getServiceName() {
        return serviceName;
    }
}

