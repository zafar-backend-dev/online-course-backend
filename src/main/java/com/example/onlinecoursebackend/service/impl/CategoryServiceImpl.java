package com.example.onlinecoursebackend.service.impl;

import com.example.onlinecoursebackend.db.entity.course.Category;
import com.example.onlinecoursebackend.db.entity.enums.UserRole;
import com.example.onlinecoursebackend.db.entity.user.User;
import com.example.onlinecoursebackend.db.repositories.CategoryRepository;
import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.category.req.AddCategoryRequestDto;
import com.example.onlinecoursebackend.dto.category.req.EditCategoryRequestDto;
import com.example.onlinecoursebackend.dto.category.res.CategoryResponseDto;
import com.example.onlinecoursebackend.mapper.CategoryMapper;
import com.example.onlinecoursebackend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository
            categoryRepository;
    private final com.example.onlinecoursebackend.db.repositories.UserRepository userRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, com.example.onlinecoursebackend.db.repositories.UserRepository userRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ApiResponse<Void> createCategory(AddCategoryRequestDto req, UUID userid) {

        User user = userRepository.findById(userid).orElse(null);

        if (user == null) {
            return new ApiResponse<>(false, "Kullanıcı bulunamadı", null);
        }


        UserRole role = user.getRole();

        if (role != UserRole.ADMIN && role != UserRole.SUPER_ADMIN) {
            return new ApiResponse<>(false, "Bu işlemi yapma yetkiniz yok", null);
        }


        if (!user.getEnabled()) {
            return new ApiResponse<>(false, "Kullanıcı aktif değil", null);
        }

        Category parent = null;
        if (req.getParentId() != null) {
            parent = categoryRepository.findById(req.getParentId())
                    .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        }

        Category category = new Category();
        category.setName(req.getName());
        category.setOrderIndex(req.getOrderIndex());
        category.setParent(parent);
        category.setCreatedBy(user);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        categoryRepository.save(category);

        return new ApiResponse<>(true, "Kategori başarıyla oluşturuldu", null);
    }

    @Override
    public ApiResponse<CategoryResponseDto> findById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        if (!category.getActive()) {
            return new ApiResponse<>(false, "Kategori aktif değil", null);
        }
        CategoryResponseDto toDto = categoryMapper.toDto(category, categoryRepository);
        return new ApiResponse<>(true, "Kategori başarıyla getirildi", toDto);
    }

    @Override
    public ApiResponse<Void> editCategory(EditCategoryRequestDto req, UUID userid, Long categoryId) {
        User user = userRepository.findById(userid).orElse(null);

        if (user == null) {
            return new ApiResponse<>(false, "Kullanıcı bulunamadı", null);
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        if (!category.getActive()) {
            return new ApiResponse<>(false, "Kategori aktif değil", null);
        }

        category.setUpdatedAt(LocalDateTime.now());
        category.setUpdatedBy(user);
        category.setName(req.getName());
        category.setOrderIndex(req.getOrderIndex());
        category = categoryRepository.save(category);
        return new ApiResponse<>(true, "Kategori başarıyla güncellendi", null);
    }

    @Override
    public ApiResponse<List<CategoryResponseDto>> list(UUID userid) {
        List<Category> categories = categoryRepository.getParents();
        List<CategoryResponseDto> toDto = categories.stream()
                .map(category -> categoryMapper.toDto(category, categoryRepository))
                .toList();
        return new ApiResponse<>(true, "Kategori listesi başarıyla getirildi", toDto);
    }

    @Override
    public ApiResponse<Void> deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        if (!category.getActive()) {
            return new ApiResponse<>(false, "Kategori aktif değil", null);
        }
        category.setActive(false);
        categoryRepository.save(category);
        return new ApiResponse<>(true, "Kategori başarıyla silindi", null);
    }
}
