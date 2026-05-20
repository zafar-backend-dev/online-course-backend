package com.example.onlinecoursebackend.db.entity.enums;

public enum NotificationType {
    IN_APP("Uygulama içi bildirim"),
    EMAIL("E-posta bildirimi");

    private final String description;

    NotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}