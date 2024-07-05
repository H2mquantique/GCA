package com.vibestechsolution.cabinetavocat;

public class ServiceSelectionRequest {
    private Long userId;
    private String serviceName;

    // Constructeurs, getters et setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
