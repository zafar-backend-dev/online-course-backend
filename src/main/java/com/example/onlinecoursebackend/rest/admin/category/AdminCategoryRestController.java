package com.example.onlinecoursebackend.rest.admin.category;

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
@RequestMapping("/api/admin/categories")

public class AdminCategoryRestController {

    private final CategoryService categoryService;


    public AdminCategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("create")
    public ApiResponse<Void> createCategory(
            @RequestBody AddCategoryRequestDto request,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return categoryService.createCategory(request, userPrincipal.getUser().getPkey());
    }

    @GetMapping("list")
    public ApiResponse<List<CategoryResponseDto>> getAllCategories(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return categoryService.list(userPrincipal.getUser().getPkey());
    }

    @PutMapping("edit/{categoryId}")
    public ApiResponse<Void> editCategory(
            @PathVariable("categoryId") Long categoryId,
            @RequestBody EditCategoryRequestDto request,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return categoryService.editCategory(request, userPrincipal.getUser().getPkey(), categoryId);
    }
    @DeleteMapping("delete/{categoryId}")
    public ApiResponse<Void> deleteCategory(
            @PathVariable Long categoryId,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping("category-by-id/{categoryId}")
    public ApiResponse<CategoryResponseDto> getAllCategories(@PathVariable("categoryId") Long categoryId){
        return categoryService.findById(categoryId);
    }
}
