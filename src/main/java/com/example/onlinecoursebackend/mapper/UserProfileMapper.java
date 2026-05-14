package com.example.onlinecoursebackend.mapper;

import com.example.onlinecoursebackend.db.entity.user.UserProfile;
import com.example.onlinecoursebackend.db.entity.user.UserProfileImage;
import com.example.onlinecoursebackend.db.entity.user.UserSocialLink;
import com.example.onlinecoursebackend.dto.user.UserProfileResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserProfileMapper {
    public UserProfileResponseDto toDto(UserProfile profile, List<UserProfileImage> images, List<UserSocialLink> links){
        UserProfileResponseDto dto = new UserProfileResponseDto();
        dto.setId(profile.getId());

        dto.setBio(profile.getBio());
        dto.setPhone(profile.getPhone());
        dto.setCountry(profile.getCountry());
        dto.setCity(profile.getCity());
        dto.setBirthdate(profile.getBirthdate());
        List<UserProfileResponseDto.ProfileImageResponseDto> userImages = new ArrayList<>();
        List<UserProfileResponseDto.SocialLinkResponseDto> socialLinks = new ArrayList<>();
        if (images != null) {
            for (UserProfileImage image : images) {
                UserProfileResponseDto.ProfileImageResponseDto imageDto = new UserProfileResponseDto.ProfileImageResponseDto();
                imageDto.setId(image.getId());
                imageDto.setImgName(image.getImgName());
                imageDto.setImgUrl(image.getImgUrl());
                imageDto.setImgSize(image.getImgSize());
                imageDto.setMain(image.getMain());
                imageDto.setCreatedAt(image.getCreatedAt());
                imageDto.setUpdatedAt(image.getUpdatedAt());
                userImages.add(imageDto);
            }
        }

        if (links != null) {
            for (UserSocialLink link : links) {

                UserProfileResponseDto.SocialLinkResponseDto l = new UserProfileResponseDto.SocialLinkResponseDto();
                l.setId(link.getId());
                l.setPlatform(link.getPlatform());
                l.setUrl(link.getUrl());
                socialLinks.add(l);
            }
        }
        dto.setProfileImages(userImages);
        dto.setSocialLinks(socialLinks);
        return dto;
    }
}
