package com.example.onlinecoursebackend.rest.user;

import com.example.onlinecoursebackend.dto.ApiResponse;
import com.example.onlinecoursebackend.security.UserPrincipal;
import com.example.onlinecoursebackend.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
