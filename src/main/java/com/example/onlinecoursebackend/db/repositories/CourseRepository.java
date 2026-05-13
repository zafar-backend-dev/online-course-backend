package com.example.onlinecoursebackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<com.example.onlinecoursebackend.db.entity.course.Course, Long> {
}
