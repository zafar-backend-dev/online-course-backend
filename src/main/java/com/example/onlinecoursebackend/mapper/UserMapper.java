package com.example.onlinecoursebackend.mapper;

import com.example.onlinecoursebackend.db.entity.user.User;
import com.example.onlinecoursebackend.dto.auth.response.AuthUserResponse;
import com.example.onlinecoursebackend.dto.user.UserProfileResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public AuthUserResponse toDto(User user, String accessToken) {
        if (user == null)
            return null;
        AuthUserResponse response = new AuthUserResponse();
        response.setPkey(user.getPkey());
        response.setFullName(user.getFullName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setBirthdate(user.getBirthdate());
        response.setRole(user.getRole());
        response.setEnabled(user.getEnabled());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

         if (accessToken != null)  response.setAccessToken(accessToken);

        return response;
    }
}
