package com.example.onlinecoursebackend.dto.user.req;

import com.example.onlinecoursebackend.db.entity.enums.SocialPlatform;
import com.example.onlinecoursebackend.db.entity.user.UserSocialLink;

public class AddEditSocialLinkRequestDto {
    private SocialPlatform platform;
    private String url;

    public SocialPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(SocialPlatform platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
