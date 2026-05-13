package com.example.onlinecoursebackend.service;

//import uz.zafar.onlineshoptelegrambot.dto.ResponseDto;
//import uz.zafar.onlineshoptelegrambot.dto.auth.*;
//import uz.zafar.onlineshoptelegrambot.dto.auth.response.TokenResponse;

import com.example.onlinecoursebackend.dto.ResponseDto;
import com.example.onlinecoursebackend.dto.auth.*;
import com.example.onlinecoursebackend.dto.auth.response.TokenResponse;

public interface AuthService {
    ResponseDto<Void> signIn(LoginForm form);

    ResponseDto<TokenResponse> verifySignIn(SignInVerifyRequest request);

    ResponseDto<Object> signUp(SignUpRequest request);

    ResponseDto<TokenResponse> verifySignUp(SignUpVerifyRequest request);

    ResponseDto<Object> forgotPassword(ForgotPasswordRequest request);

    ResponseDto<Object> resetPassword(ResetPasswordRequest request);
}
