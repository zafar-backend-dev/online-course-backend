 package com.example.onlinecoursebackend.db.entity.user;

import com.example.onlinecoursebackend.db.entity.enums.SocialPlatform;
import jakarta.persistence.*;

import java.util.UUID;

 @Entity
@Table(name = "user_social_links")
public class UserSocialLink {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(columnDefinition = "uuid")
     private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialPlatform platform;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;
    private Boolean active = true;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public SocialPlatform getPlatform() { return platform; }
    public void setPlatform(SocialPlatform platform) { this.platform = platform; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public UserProfile getUserProfile() { return userProfile; }
    public void setUserProfile(UserProfile userProfile) { this.userProfile = userProfile; }

     public Boolean getActive() {
         return active;
     }

     public void setActive(Boolean active) {
         this.active = active;
     }
 }