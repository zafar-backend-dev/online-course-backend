package com.example.onlinecoursebackend.db.entity.enums;

public enum CourseStatus {
    ACTIVE("Yayında, öğrenciler kayıt olabilir"),
    INACTIVE("Geçici olarak kapatıldı"),
    DRAFT("Öğretmen hâlâ hazırlıyor"),
    PENDING("Admin onayı bekliyor"),
    REJECTED("Admin onaylamadı");

    private final String description;

    CourseStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}