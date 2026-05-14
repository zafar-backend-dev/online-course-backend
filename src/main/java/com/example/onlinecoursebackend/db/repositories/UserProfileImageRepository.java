package com.example.onlinecoursebackend.db.repositories;

import com.example.onlinecoursebackend.db.entity.user.UserProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserProfileImageRepository extends JpaRepository<UserProfileImage, UUID> {
    @Query("select upi from UserProfileImage upi where upi.userProfile.id = :userProfileId and upi.active=true")
    List<UserProfileImage> findByUserProfileId(@Param("userProfileId") UUID userProfileId);
}
