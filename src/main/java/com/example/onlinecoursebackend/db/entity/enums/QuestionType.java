package com.example.onlinecoursebackend.db.entity.enums;

public enum QuestionType {
    SINGLE("Tek doğru cevap"),
    MULTIPLE("Çoklu doğru cevap"),
    TRUE_FALSE("Doğru / Yanlış");

    private final String description;

    QuestionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}