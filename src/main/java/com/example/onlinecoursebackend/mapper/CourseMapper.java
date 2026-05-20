package com.example.onlinecoursebackend.mapper;

import com.example.onlinecoursebackend.db.entity.course.Course;
import com.example.onlinecoursebackend.dto.course.res.CourseResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseResponseDto toDto(Course c){
        return new CourseResponseDto(
                c.getId(),
                c.getName(),
                c.getDescription(),
                c.getActive(),
                c.getStatus(),
                c.getOrderIndex(),
                c.getImageUrl(),
                c.getImgSize(),
                c.getImgName(),
                c.getCategory().getId(),
                c.getTeacher().getPkey(),
                c.getWhatsappGroupLink(),
                c.getCreatedAt(),
                c.getUpdatedAt()
        );
    }
}
