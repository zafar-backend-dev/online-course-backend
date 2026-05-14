package com.example.onlinecoursebackend.dto.category.res;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CategoryResponseDto {
    private Long id;
    private String name;
    private Integer orderIndex;
    private Long parentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID createdUserId;
    private UUID updatedUserId;
    private List<CategoryResponseDto> children;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(UUID createdUserId) {
        this.createdUserId = createdUserId;
    }

    public UUID getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(UUID updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    public List<CategoryResponseDto> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryResponseDto> children) {
        this.children = children;
    }
}
