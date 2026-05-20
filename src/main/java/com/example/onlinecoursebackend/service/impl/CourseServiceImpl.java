package com.example.onlinecoursebackend.service.impl;

import com.example.onlinecoursebackend.db.entity.course.Category;
import com.example.onlinecoursebackend.db.entity.course.Course;
import com.example.onlinecoursebackend.db.entity.enums.CourseStatus;
import com.example.onlinecoursebackend.db.entity.enums.UserRole;
import com.example.onlinecoursebackend.db.entity.user.User;
import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.course.req.AddCourseRequestDto;
import com.example.onlinecoursebackend.dto.course.req.EditCourseRequestDto;
import com.example.onlinecoursebackend.dto.course.res.CourseResponseDto;
import com.example.onlinecoursebackend.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    private final com.example.onlinecoursebackend.db.repositories.CourseRepository courseRepository;
    private final com.example.onlinecoursebackend.db.repositories.UserRepository userRepository;
    private final com.example.onlinecoursebackend.mapper.CourseMapper courseMapper;
    private final com.example.onlinecoursebackend.db.repositories.CategoryRepository categoryRepository;

    public CourseServiceImpl(com.example.onlinecoursebackend.db.repositories.CourseRepository courseRepository, com.example.onlinecoursebackend.db.repositories.UserRepository userRepository, com.example.onlinecoursebackend.mapper.CourseMapper courseMapper, com.example.onlinecoursebackend.db.repositories.CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseMapper = courseMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse<Void> addCourse(AddCourseRequestDto req, User user) {
        if (!user.getRole().equals(UserRole.TEACHER)) {
            throw new RuntimeException("Yalnızca öğretmenler kurs ekleyebilir");
        }

        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        Course course = new Course();
        course.setName(req.getName());
        course.setDescription(req.getDescription());
        course.setActive(true);
        course.setStatus(CourseStatus.DRAFT);
        course.setOrderIndex(req.getOrderIndex());
        course.setImageUrl(req.getImageUrl());
        course.setImgSize(req.getImgSize());
        course.setImgName(req.getImgName());
        course.setCategory(category);
        course.setTeacher(user);
        course.setWhatsappGroupLink(req.getWhatsappGroupLink());
        courseRepository.save(course);

        return new ApiResponse<>(true, "Kurs başarıyla eklendi", null);
    }

    @Override
    public ApiResponse<Void> updateCourse(Long courseId, EditCourseRequestDto req, User user) {
        if (!user.getRole().equals(UserRole.TEACHER)) {
            throw new RuntimeException("Yalnızca öğretmenler kurs güncelleyebilir");
        }

        if (courseId == null) {
            throw new RuntimeException("Kurs ID'si boş olamaz");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Kurs bulunamadı"));
        if (!course.getActive()) {
            throw new RuntimeException("Bu kurs zaten silinmiş");
        }
        if (course.getTeacher() == null || !course.getTeacher().getPkey().equals(user.getPkey())) {
            throw new RuntimeException("Bu kursu güncelleme yetkiniz yok");
        }

        course.setName(req.getName());
        course.setDescription(req.getDescription());
        course.setOrderIndex(req.getOrderIndex());
        course.setImageUrl(req.getImageUrl());
        course.setImgSize(req.getImgSize());
        course.setImgName(req.getImgName());
        course.setWhatsappGroupLink(req.getWhatsappGroupLink());
        courseRepository.save(course);
        return new ApiResponse<>(true, "Kurs başarıyla güncellendi", null);
    }

    /*
        @Override
        public ApiResponse<Page<CourseResponseDto>> getTeacherCourses(User user, int page, int size) {
            if (!user.getRole().equals(UserRole.TEACHER)) {
                throw new RuntimeException("Yalnızca öğretmenler kurslarını görebilir");
            }

            if (courseRepository.findByTeacherId(user.getPkey()).isEmpty())
                return new ApiResponse<>(true, "Kurs bulunamadı", Page.empty());
            Page<Course> courses = courseRepository.findByTeacherId(PageRequest.of(page, size),user.getPkey());
            Page<CourseResponseDto> courseDtos = courses.map(courseMapper::toDto);

            return new ApiResponse<>(true, "Kurslar başarıyla getirildi", courseDtos);
        }
    */
    @Override
    public ApiResponse<Page<CourseResponseDto>> getTeacherCourses(User user, int page, int size) {
        if (!user.getRole().equals(UserRole.TEACHER)) {
            throw new RuntimeException("Yalnızca öğretmenler kurslarını görebilir");
        }

        List<Course> allCourses = courseRepository.findByTeacherId(user.getPkey());

        if (allCourses.isEmpty())
            return new ApiResponse<>(true, "Kurs bulunamadı", Page.empty());

        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min(start + size, allCourses.size());

        List<Course> paged = allCourses.subList(start, end);

        Page<CourseResponseDto> result = new PageImpl<>(
                paged.stream().map(courseMapper::toDto).toList(),
                pageable,
                allCourses.size()
        );

        return new ApiResponse<>(true, "Kurslar başarıyla getirildi", result);
    }

    @Override
    public ApiResponse<Void> deleteCourse(Long courseId, UUID userId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Kurs bulunamadı"));
        if (!course.getActive()) {
            throw new RuntimeException("Bu kurs zaten silinmiş");
        }

        if (!course.getTeacher().getPkey().equals(userId)) {
            throw new RuntimeException("Bu kursu silme yetkiniz yok");
        }

        course.setActive(false);
        courseRepository.save(course);
        return new ApiResponse<>(true, "Kurs başarıyla silindi", null);
    }

    @Override
    public ApiResponse<CourseResponseDto> getById(Long courseId, UUID userId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Kurs bulunamadı"));

        if (!course.getTeacher().getPkey().equals(userId) && !course.getActive()) {
            throw new RuntimeException("Bu kursa erişim izniniz yok");
        }
        if (!course.getActive()) {
            throw new RuntimeException("Bu kurs zaten silinmiş");
        }
        CourseResponseDto courseDto = courseMapper.toDto(course);
        return new ApiResponse<>(true, "Kurs başarıyla getirildi", courseDto);
    }
}
