package com.example.onlinecoursebackend.service;

import com.example.onlinecoursebackend.db.entity.user.User;
import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.course.req.AddCourseRequestDto;
import com.example.onlinecoursebackend.dto.course.req.EditCourseRequestDto;
import com.example.onlinecoursebackend.dto.course.res.CourseResponseDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CourseService {
 ApiResponse<Void> addCourse(AddCourseRequestDto req, User user);

 ApiResponse<Void> updateCourse(Long courseId, EditCourseRequestDto req, User user);

 ApiResponse<Void> deleteCourse(Long courseId, UUID userId);

 ApiResponse<Page<CourseResponseDto>> getTeacherCourses(User user, int page, int size);

 ApiResponse<CourseResponseDto> getById(Long courseId, UUID userId);

}
