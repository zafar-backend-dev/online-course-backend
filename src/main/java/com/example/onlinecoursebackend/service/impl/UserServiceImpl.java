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
import com.example.onlinecoursebackend.dto.ResponseDto;
import com.example.onlinecoursebackend.dto.enums.ErrorCode;
import com.example.onlinecoursebackend.dto.user.UserProfileResponseDto;
import com.example.onlinecoursebackend.dto.user.req.AddEditSocialLinkRequestDto;
import com.example.onlinecoursebackend.dto.user.req.AddProfileImageRequestDto;
import com.example.onlinecoursebackend.dto.user.req.CreateProfileRequestDto;
import com.example.onlinecoursebackend.mapper.UserProfileMapper;
import com.example.onlinecoursebackend.service.UserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @Value("${file.upload.dir}")
    private String uploadDirectory;

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
        if (profile == null) {
            return new ApiResponse<>(false, "Profil bulunamadı", null);
        }
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

        User user = userRepository.findById(userid).orElse(null);
        if (user == null) {
            return new ApiResponse<>(false, "Kullanıcı bulunamadı", null);
        }

        UserProfile profile = userProfileRepository.findByUserId(userid).orElse(null);
        if (profile == null) {
            return new ApiResponse<>(false, "Profil bulunamadı", null);
        }

        UserProfileImage image = new UserProfileImage();
        image.setImgName(req.getImgName());
        image.setImgUrl(req.getImgUrl());
        image.setImgSize(req.getImgSize());
        image.setUserProfile(profile);
        image.setCreatedAt(LocalDateTime.now());
        image.setUpdatedAt(LocalDateTime.now());
        image.setActive(true);

        List<UserProfileImage> images =
                userProfileImageRepository.findByUserProfileId(profile.getId());

        for (UserProfileImage i : images) {
            i.setMain(false);
            userProfileImageRepository.save(i);
        }

        image.setMain(true);
        userProfileImageRepository.save(image);

        return new ApiResponse<>(true, "Profil resmi başarıyla eklendi");
    }

    @Override
    public ApiResponse<Void> deleteImage(UUID userid, UUID imageId) {
        User user = userRepository.findById(userid).orElse(null);
        if (user == null) {
            return new ApiResponse<>(false, "Kullanıcı bulunamadı", null);
        }

        UserProfile profile = userProfileRepository.findByUserId(userid).orElse(null);
        if (profile == null) {
            return new ApiResponse<>(false, "Profil bulunamadı", null);
        }

        UserProfileImage image = userProfileImageRepository.findById(imageId).orElse(null);
        if (image == null || !image.getActive() || !image.getUserProfile().getId().equals(profile.getId())) {
            return new ApiResponse<>(false, "Resim bulunamadı", null);
        }
        image.setActive(false);
        userProfileImageRepository.save(image);
        try {
            String fileName = image.getImgUrl().replace("/uploads/", "");

            Path filePath = Paths.get(uploadDirectory, fileName);

            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse<>(true, "Profil resmi başarıyla silindi");
    }

    @Override
    public ApiResponse<Void> addSocialLink(AddEditSocialLinkRequestDto req, UUID userid) {
        User user = userRepository.findById(userid).orElse(null);
        if (user == null) {
            return new ApiResponse<>(false, "Kullanıcı bulunamadı", null);
        }

        UserProfile profile = userProfileRepository.findByUserId(userid).orElse(null);
        if (profile == null) {
            return new ApiResponse<>(false, "Profil bulunamadı", null);
        }
        UserSocialLink socialLink = new UserSocialLink();
        socialLink.setPlatform(req.getPlatform());
        socialLink.setUrl(req.getUrl());
        socialLink.setUserProfile(profile);
        userSocialLinkRepository.save(socialLink);
        return new ApiResponse<>(true, "Sosyal bağlantı başarıyla eklendi");
    }

    @Override
    public ApiResponse<Void> editSocialLink(AddEditSocialLinkRequestDto req, UUID userid, UUID socialLinkId) {
        User user = userRepository.findById(userid).orElse(null);
        if (user == null) {
            return new ApiResponse<>(false, "Kullanıcı bulunamadı", null);
        }

        UserProfile profile = userProfileRepository.findByUserId(userid).orElse(null);
        if (profile == null) {
            return new ApiResponse<>(false, "Profil bulunamadı", null);
        }
        UserSocialLink socialLink = userSocialLinkRepository.findById(socialLinkId).orElse(null);
        if (socialLink == null || !socialLink.getUserProfile().getId().equals(profile.getId())) {
            return new ApiResponse<>(false, "Sosyal bağlantı bulunamadı", null);
        }
        socialLink.setPlatform(req.getPlatform());
        socialLink.setUrl(req.getUrl());
        userSocialLinkRepository.save(socialLink);
        return new ApiResponse<>(true, "Sosyal bağlantı başarıyla güncellendi");
    }

    @Override
    public ApiResponse<Void> deleteSocialLink(UUID userid, UUID socialLinkId) {
        User user = userRepository.findById(userid).orElse(null);
        if (user == null) {
            return new ApiResponse<>(false, "Kullanıcı bulunamadı", null);
        }

        UserProfile profile = userProfileRepository.findByUserId(userid).orElse(null);
        if (profile == null) {
            return new ApiResponse<>(false, "Profil bulunamadı", null);
        }
        UserSocialLink socialLink = userSocialLinkRepository.findById(socialLinkId).orElse(null);
        if (socialLink == null || !socialLink.getActive() || !socialLink.getUserProfile().getId().equals(profile.getId())) {
            return new ApiResponse<>(false, "Sosyal bağlantı bulunamadı", null);
        }
        socialLink.setActive(false);
        userSocialLinkRepository.save(socialLink);
        return new ApiResponse<>(true, "Sosyal bağlantı başarıyla silindi");
    }
}
