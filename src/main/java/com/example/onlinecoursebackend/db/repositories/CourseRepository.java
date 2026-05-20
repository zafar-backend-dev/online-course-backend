package com.example.onlinecoursebackend.db.repositories;

import com.example.onlinecoursebackend.db.entity.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<com.example.onlinecoursebackend.db.entity.course.Course, Long> {
    @Query("select c from Course c where c.teacher.pkey = :teacherId and c.active=true order by c.createdAt desc")
    Page<Course> findByTeacherId(Pageable pageable,@Param("teacherId") UUID teacherId);

    @Query("select c from Course c where c.teacher.pkey = :teacherId and c.active=true order by c.createdAt desc")
    List<Course> findByTeacherId(@Param("teacherId") UUID teacherId);

    @Query("select c from Course c where c.teacher.pkey = :teacherId and c.id = :courseId and c.active=true")
    Page<Course> findByTeacherIdAndCourseId(@Param("teacherId") UUID teacherId,@Param("courseId") Long courseId, Pageable pageable);
}
