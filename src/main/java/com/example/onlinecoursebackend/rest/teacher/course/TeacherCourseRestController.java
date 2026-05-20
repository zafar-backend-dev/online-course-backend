package com.example.onlinecoursebackend.rest.teacher.course;

import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.course.req.AddCourseRequestDto;
import com.example.onlinecoursebackend.dto.course.req.EditCourseRequestDto;
import com.example.onlinecoursebackend.security.UserPrincipal;
import com.example.onlinecoursebackend.service.CourseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/courses")
public class TeacherCourseRestController {
    private final CourseService courseService;

    public TeacherCourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("my-courses")
    public ApiResponse<?> getTeacherCourses(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                            @RequestParam(defaultValue = "0", name = "page") int page,
                                            @RequestParam(defaultValue = "10", name = "size") int size) {
        return courseService.getTeacherCourses(userPrincipal.getUser(), page, size);
    }
    @PostMapping("add-course")
    public ApiResponse<?> addCourse(@RequestBody AddCourseRequestDto request, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseService.addCourse(request, userPrincipal.getUser());
    }

     @GetMapping("{courseId}")
    public ApiResponse<?> getCourseById(@PathVariable Long courseId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseService.getById(courseId, userPrincipal.getUser().getPkey());
    }

    @PutMapping("update/{courseId}")
    public ApiResponse<?> updateCourse(@PathVariable Long courseId, @RequestBody EditCourseRequestDto request, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseService.updateCourse(courseId, request, userPrincipal.getUser());
    }

    @DeleteMapping("/delete/{courseId}")
    public ApiResponse<?> deleteCourse(@PathVariable Long courseId, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return courseService.deleteCourse(courseId, userPrincipal.getUser().getPkey());
    }
}
