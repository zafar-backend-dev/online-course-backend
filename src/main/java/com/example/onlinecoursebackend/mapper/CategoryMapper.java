package com.example.onlinecoursebackend.mapper;

import com.example.onlinecoursebackend.db.entity.course.Category;
import com.example.onlinecoursebackend.db.repositories.CategoryRepository;
import com.example.onlinecoursebackend.dto.category.res.CategoryResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    /*public com.example.onlinecoursebackend.dto.category.res.CategoryResponseDto toDto(com.example.onlinecoursebackend.db.entity.course.Category category, CategoryRepository categoryRepository) {
        if (category == null)
            return null;
        com.example.onlinecoursebackend.dto.category.res.CategoryResponseDto response = new com.example.onlinecoursebackend.dto.category.res.CategoryResponseDto();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setOrderIndex(category.getOrderIndex());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());
        response.setCreatedUserId(category.getCreatedBy() != null ? category.getCreatedBy().getPkey() : null);
        response.setUpdatedUserId(category.getUpdatedBy() != null ? category.getUpdatedBy().getPkey() : null);
        response.setParentId(category.getParent() != null ? category.getParent().getId() : null);
        categoryRepository.getChildren(category.getId());
        return response;
    }*/
    public CategoryResponseDto toDto(Category category, CategoryRepository categoryRepository) {

        if (category == null) {
            return null;
        }

        CategoryResponseDto response = new CategoryResponseDto();

        response.setId(category.getId());
        response.setName(category.getName());
        response.setOrderIndex(category.getOrderIndex());
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());

        response.setCreatedUserId(
                category.getCreatedBy() != null ? category.getCreatedBy().getPkey() : null
        );

        response.setUpdatedUserId(
                category.getUpdatedBy() != null ? category.getUpdatedBy().getPkey() : null
        );

        response.setParentId(
                category.getParent() != null ? category.getParent().getId() : null
        );

        List<Category> children = categoryRepository.getChildren(category.getId());

        List<CategoryResponseDto> childDtos;

        childDtos = children.isEmpty() ? new ArrayList<>() : children.stream()
                .map(child -> toDto(child, categoryRepository))
                .toList();
        response.setChildren( childDtos);

        return response;
    }
}
