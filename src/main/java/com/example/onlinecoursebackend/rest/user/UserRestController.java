package com.example.onlinecoursebackend.rest.user;

import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.dto.user.req.AddEditSocialLinkRequestDto;
import com.example.onlinecoursebackend.dto.user.req.AddProfileImageRequestDto;
import com.example.onlinecoursebackend.dto.user.req.CreateProfileRequestDto;
import com.example.onlinecoursebackend.security.UserPrincipal;
import com.example.onlinecoursebackend.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")

public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("my-profile")
    public ApiResponse<?> myProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userService.myProfile(userPrincipal.getUser().getPkey());
    }
    @DeleteMapping("profile/delete/{imageId}")
    public ApiResponse<?> deleteImage(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("imageId")UUID imageId) {
        return userService.deleteImage(userPrincipal.getUser().getPkey(), imageId);
    }

    @PostMapping("create-profile")
    public ApiResponse<?> createProfile(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody CreateProfileRequestDto req) {
        return userService.createProfile(req, userPrincipal.getUser().getPkey());
    }

    @PostMapping("add-image-to-profile")
    public ApiResponse<?> addImageToProfile(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody AddProfileImageRequestDto req) {
        return userService.addImage(req, userPrincipal.getUser().getPkey());
    }

     @PostMapping("add-social-link")
    public ApiResponse<?> addEditSocialLink(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody AddEditSocialLinkRequestDto req) {
        return userService.addSocialLink(req, userPrincipal.getUser().getPkey());
    }

    @PutMapping("profile/social-link/edit/{socialLinkId}")
    public ApiResponse<?> editSocialLink(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody AddEditSocialLinkRequestDto req, @PathVariable("socialLinkId") UUID socialLinkId) {
        return userService.editSocialLink(req, userPrincipal.getUser().getPkey(), socialLinkId);
    }
    @DeleteMapping("profile/social-link/delete/{socialLinkId}")
    public ApiResponse<?> deleteSocialLink(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable("socialLinkId") UUID socialLinkId) {
        return userService.deleteSocialLink(userPrincipal.getUser().getPkey(), socialLinkId);
    }
}
