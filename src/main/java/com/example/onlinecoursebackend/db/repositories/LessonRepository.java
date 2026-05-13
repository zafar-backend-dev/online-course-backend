package com.example.onlinecoursebackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<com.example.onlinecoursebackend.db.entity.category.Lesson, UUID> {
}
