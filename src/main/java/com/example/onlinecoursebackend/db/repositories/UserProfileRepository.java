package com.example.onlinecoursebackend.db.repositories;

import com.example.onlinecoursebackend.db.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    @Query("SELECT u FROM UserProfile u WHERE u.user.pkey = :userid")
    Optional<UserProfile> findByUserId(@Param("userid") UUID userId);
}
