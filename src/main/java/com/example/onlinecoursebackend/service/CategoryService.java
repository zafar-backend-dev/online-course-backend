package com.example.onlinecoursebackend.service;

import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.category.req.AddCategoryRequestDto;
import com.example.onlinecoursebackend.dto.category.req.EditCategoryRequestDto;
import com.example.onlinecoursebackend.dto.category.res.CategoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    ApiResponse<Void> createCategory(AddCategoryRequestDto req,UUID userid);
    ApiResponse<Void> editCategory(EditCategoryRequestDto req, UUID userid, Long categoryId);
    ApiResponse<List<CategoryResponseDto>> list(UUID userid);
    ApiResponse<CategoryResponseDto> findById(Long categoryId);
    ApiResponse<Void> deleteCategory(Long categoryId);
}
