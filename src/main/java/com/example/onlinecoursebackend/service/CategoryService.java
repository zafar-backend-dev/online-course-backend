package com.example.onlinecoursebackend.service;

import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.category.req.AddCategoryRequestDto;

import java.util.UUID;

public interface CategoryService {
    ApiResponse<Void> createCategory(AddCategoryRequestDto req);
}
