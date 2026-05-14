package com.example.onlinecoursebackend.dto.user;

import com.example.onlinecoursebackend.db.entity.enums.SocialPlatform;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserProfileResponseDto {
    private UUID id;
    private String bio;
    private String phone;
    private String country;
    private String city;
    private LocalDate birthdate;
    private List<SocialLinkResponseDto> socialLinks;
    private List<ProfileImageResponseDto> profileImages;

    public List<ProfileImageResponseDto> getProfileImages() {
        return profileImages;
    }

    public void setProfileImages(List<ProfileImageResponseDto> profileImages) {
        this.profileImages = profileImages;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<SocialLinkResponseDto> getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(List<SocialLinkResponseDto> socialLinkResponseDtos) {
        this.socialLinks = socialLinkResponseDtos;
    }
    public static class ProfileImageResponseDto{
        private UUID id;
        private String imgName;
        private String imgUrl;
        private Long imgSize;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

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
    }
    public static class SocialLinkResponseDto {
        private UUID id;
        private SocialPlatform platform;
        private String url;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

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
}
