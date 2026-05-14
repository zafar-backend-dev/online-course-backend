package com.example.onlinecoursebackend.service.impl;

import com.example.onlinecoursebackend.db.entity.user.User;
import com.example.onlinecoursebackend.db.entity.user.UserProfile;
import com.example.onlinecoursebackend.db.entity.user.UserProfileImage;
import com.example.onlinecoursebackend.db.entity.user.UserSocialLink;
import com.example.onlinecoursebackend.db.repositories.UserProfileImageRepository;
import com.example.onlinecoursebackend.db.repositories.UserProfileRepository;
import com.example.onlinecoursebackend.db.repositories.UserRepository;
import com.example.onlinecoursebackend.db.repositories.UserSocialLinkRepository;
import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.user.UserProfileResponseDto;
import com.example.onlinecoursebackend.dto.user.req.AddEditSocialLinkRequestDto;
import com.example.onlinecoursebackend.dto.user.req.AddProfileImageRequestDto;
import com.example.onlinecoursebackend.dto.user.req.CreateProfileRequestDto;
import com.example.onlinecoursebackend.mapper.UserProfileMapper;
import com.example.onlinecoursebackend.service.UserService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserProfileRepository userProfileRepository;
    private final UserSocialLinkRepository userSocialLinkRepository;
    private final UserProfileMapper userProfileMapper;
    private final UserProfileImageRepository userProfileImageRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(UserProfileRepository userProfileRepository, UserSocialLinkRepository userSocialLinkRepository, UserProfileMapper userProfileMapper, UserProfileImageRepository userProfileImageRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userSocialLinkRepository = userSocialLinkRepository;
        this.userProfileMapper = userProfileMapper;
        this.userProfileImageRepository = userProfileImageRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ApiResponse<UserProfileResponseDto> myProfile(UUID userid) {
        UserProfile profile = userProfileRepository.findByUserId(userid).orElse(null);
        List<UserProfileImage> images = userProfileImageRepository.findByUserProfileId(profile.getId());
        List<UserSocialLink> links = userSocialLinkRepository.findByUserProfileId(profile.getId());
        return new ApiResponse<>(true, "Başarılı", userProfileMapper.toDto(profile, images == null ? new ArrayList<>() : images, links == null ? new ArrayList<>() : links));
    }

    @Override
    public ApiResponse<Void> createProfile(CreateProfileRequestDto req, UUID userid) {
        User user = userRepository.findById(userid).orElse(null);
        UserProfile profile = userProfileRepository.findByUserId(userid).orElse(null);

        if (user == null) {
            return new ApiResponse<>(false, "Kullanıcı bulunamadı", null);
        }

        if (!user.getEnabled()) {
            return new ApiResponse<>(false, "Kullanıcı aktif değil", null);
        }

        if (profile != null) {
            return new ApiResponse<>(false, "Profil zaten mevcut", null);
        }


        profile = new UserProfile();
        profile.setBio(req.getBio());
        profile.setPhone(req.getPhone());
        profile.setCountry(req.getCountry());
        profile.setCity(req.getCity());
        profile.setBirthdate(req.getBirthdate());
        profile.setUser(user);
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());
        userProfileRepository.save(profile);
        return new ApiResponse<>(true, "Profil başarıyla oluşturuldu");

    }

    @Override
    public ApiResponse<Void> addImage(AddProfileImageRequestDto req, UUID userid) {
        return null;
    }

    @Override
    public ApiResponse<Void> addEditSocialLink(AddEditSocialLinkRequestDto req, UUID userid) {
        return null;
    }
}
