package com.example.onlinecoursebackend.service;

import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.user.UserProfileResponseDto;
import com.example.onlinecoursebackend.dto.user.req.AddEditSocialLinkRequestDto;
import com.example.onlinecoursebackend.dto.user.req.AddProfileImageRequestDto;
import com.example.onlinecoursebackend.dto.user.req.CreateProfileRequestDto;

import java.util.UUID;

public interface UserService {
    ApiResponse<UserProfileResponseDto>  myProfile(UUID userid);
    ApiResponse<Void> createProfile(CreateProfileRequestDto req,UUID userid);
    ApiResponse<Void> addImage(AddProfileImageRequestDto req,UUID userid);
    ApiResponse<Void> deleteImage(UUID userid, UUID imageId);
    ApiResponse<Void> addSocialLink(AddEditSocialLinkRequestDto req, UUID userid);
    ApiResponse<Void> editSocialLink(AddEditSocialLinkRequestDto req, UUID userid,UUID socialLinkId);
    ApiResponse<Void> deleteSocialLink(UUID userid,UUID socialLinkId);
}
