package com.example.onlinecoursebackend.db.entity.enums;

public enum FileType {
    IMAGE("image"),
    VIDEO("video"),
    DOCUMENT("document"),
    AUDIO("audio"),
    VOICE("voice"),
    OTHER("other");
    private final String desc;

    FileType(String desc) {
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }
}