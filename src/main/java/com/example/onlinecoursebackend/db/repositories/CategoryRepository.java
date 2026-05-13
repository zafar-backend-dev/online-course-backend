package com.example.onlinecoursebackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<com.example.onlinecoursebackend.db.entity.category.Category, Long> {
}
