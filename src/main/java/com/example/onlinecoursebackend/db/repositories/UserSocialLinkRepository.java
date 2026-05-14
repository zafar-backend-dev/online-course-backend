package com.example.onlinecoursebackend.db.repositories;

import com.example.onlinecoursebackend.db.entity.user.UserProfileImage;
import com.example.onlinecoursebackend.db.entity.user.UserSocialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserSocialLinkRepository extends JpaRepository<UserSocialLink, UUID> {
    @Query("select l from UserSocialLink l where l.userProfile.id = :userProfileId and l.active=true")
    List<UserSocialLink> findByUserProfileId(@Param("userProfileId") UUID userProfileId);

}