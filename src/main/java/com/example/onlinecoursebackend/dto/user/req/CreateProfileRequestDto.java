package com.example.onlinecoursebackend.dto.user.req;

import com.example.onlinecoursebackend.db.entity.user.UserProfile;

import java.time.LocalDate;

public class CreateProfileRequestDto {
    private String bio;

    private String phone;
    private String country;
    private String city;
    private LocalDate birthdate;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
