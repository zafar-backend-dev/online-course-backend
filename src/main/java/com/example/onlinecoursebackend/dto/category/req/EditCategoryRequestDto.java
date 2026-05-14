package com.example.onlinecoursebackend.dto.category.req;

public class EditCategoryRequestDto {
    private String name;
    private Integer orderIndex;

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
}
