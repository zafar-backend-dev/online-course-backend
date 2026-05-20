package com.example.onlinecoursebackend.rest.teacher.category;

import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.category.req.AddCategoryRequestDto;
import com.example.onlinecoursebackend.dto.category.req.EditCategoryRequestDto;
import com.example.onlinecoursebackend.dto.category.res.CategoryResponseDto;
import com.example.onlinecoursebackend.security.UserPrincipal;
import com.example.onlinecoursebackend.service.CategoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/categories")

public class TeacherCategoryRestController  {

    private final CategoryService categoryService;

    public TeacherCategoryRestController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("list")
    public ApiResponse<List<CategoryResponseDto>> getAllCategories(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return categoryService.list(userPrincipal.getUser().getPkey());
    }


    @GetMapping("category-by-id/{categoryId}")
    public ApiResponse<CategoryResponseDto> getAllCategories(@PathVariable("categoryId") Long categoryId){
        return categoryService.findById(categoryId);
    }
}
