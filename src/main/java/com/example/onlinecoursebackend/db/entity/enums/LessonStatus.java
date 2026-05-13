// 1. LessonStatus.java
package com.example.onlinecoursebackend.db.entity.enums;

public enum LessonStatus {
    DRAFT("Hazırlanıyor, henüz yayınlanmadı"),
    PENDING("Admin onayı bekliyor"),
    OPEN("Yayında, herkes erişebilir"),
    CLOSED("Geçici olarak kapatıldı"),
    ARCHIVED("Kalıcı olarak kaldırıldı");

    private final String description;

    LessonStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}