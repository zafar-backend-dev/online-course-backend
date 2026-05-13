package com.example.onlinecoursebackend.service;


import com.example.onlinecoursebackend.dto.enums.Purpose;

public interface EmailService {
    void send(String email, String code, String title, Purpose purpose);
}
