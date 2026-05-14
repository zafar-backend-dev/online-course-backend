package com.example.onlinecoursebackend.db.repositories;

import com.example.onlinecoursebackend.db.entity.course.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<com.example.onlinecoursebackend.db.entity.course.Category, Long> {
    @Query("select c from Category c where c.active=true and c.parent IS NULL order by c.orderIndex asc")
    List<Category> getParents();

    @Query("select c from Category c where c.active=true and c.parent.id=:parentId order by c.orderIndex asc")
    List<Category> getChildren(@Param("parentId") Long parentId);
}
