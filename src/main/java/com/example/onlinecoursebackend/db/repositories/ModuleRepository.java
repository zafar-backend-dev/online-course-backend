package com.example.onlinecoursebackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<com.example.onlinecoursebackend.db.entity.course.Module, Long> {
}
