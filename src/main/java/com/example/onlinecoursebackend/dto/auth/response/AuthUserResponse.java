package com.example.onlinecoursebackend.dto.auth.response;

import com.example.onlinecoursebackend.db.entity.enums.UserRole;
import com.example.onlinecoursebackend.dto.user.UserProfileResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class AuthUserResponse {

    private UUID pkey;
    private String fullName;
    private String username;
    private String email;
    private LocalDate birthdate;
    private UserRole role;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserProfileResponseDto getProfile() {
        return profile;
    }

    public void setProfile(UserProfileResponseDto profile) {
        this.profile = profile;
    }

    private UserProfileResponseDto profile;

    private String accessToken;
    @JsonIgnore
    private String refreshToken;


    public UUID getPkey() { return pkey; }
    public void setPkey(UUID pkey) { this.pkey = pkey; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }


    public static AuthUserResponse from(com.example.onlinecoursebackend.db.entity.user.User user,
                                        String accessToken,
                                        String refreshToken) {
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
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }
}