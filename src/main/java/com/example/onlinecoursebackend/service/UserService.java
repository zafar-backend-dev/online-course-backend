package com.example.onlinecoursebackend.service;

import com.example.onlinecoursebackend.db.entity.user.User;
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
    ApiResponse<Void> addEditSocialLink(AddEditSocialLinkRequestDto req,UUID userid);
}
