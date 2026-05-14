package com.example.onlinecoursebackend.dto.user.req;

import com.example.onlinecoursebackend.db.entity.user.UserProfileImage;

public class AddProfileImageRequestDto {
    private String imgName;
    private String imgUrl;
    private Long imgSize;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getImgSize() {
        return imgSize;
    }

    public void setImgSize(Long imgSize) {
        this.imgSize = imgSize;
    }
}
