package com.example.onlinecoursebackend.dto.course.res;

import com.example.onlinecoursebackend.db.entity.enums.CourseStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private CourseStatus status;
    private Integer orderIndex;
    private String imageUrl;
    private Long imgSize;
    private String imgName;
    private Long categoryId;
    private UUID teacherId;
    private String whatsappGroupLink;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getImgSize() {
        return imgSize;
    }

    public void setImgSize(Long imgSize) {
        this.imgSize = imgSize;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
    }

    public String getWhatsappGroupLink() {
        return whatsappGroupLink;
    }

    public void setWhatsappGroupLink(String whatsappGroupLink) {
        this.whatsappGroupLink = whatsappGroupLink;
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

    public CourseResponseDto(Long id, String name, String description, Boolean active, CourseStatus status, Integer orderIndex, String imageUrl, Long imgSize, String imgName, Long categoryId, UUID teacherId, String whatsappGroupLink, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.status = status;
        this.orderIndex = orderIndex;
        this.imageUrl = imageUrl;
        this.imgSize = imgSize;
        this.imgName = imgName;
        this.categoryId = categoryId;
        this.teacherId = teacherId;
        this.whatsappGroupLink = whatsappGroupLink;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
