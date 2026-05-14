//package com.example.onlinecoursebackend.rest.auth;
//
//import com.example.onlinecoursebackend.dto.ResponseDto;
//import com.example.onlinecoursebackend.dto.auth.*;
//import com.example.onlinecoursebackend.dto.auth.response.TokenResponse;
//import com.example.onlinecoursebackend.dto.enums.ErrorCode;
//import com.example.onlinecoursebackend.mapper.UserMapper;
//import com.example.onlinecoursebackend.security.UserPrincipal;
//import com.example.onlinecoursebackend.service.AuthService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@Tag(name = "Auth", description = "Sign-up, sign-in, verification, password reset, refresh-token va username tekshirish")
//public class AuthController {
//
//    private final AuthService authService;
//    private final UserMapper userMapper ;
//
//    public AuthController(AuthService authService, UserMapper userMapper) {
//        this.authService = authService;
//        this.userMapper = userMapper;
//    }
//    private String extractToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7);
//        }
//        return null;
//    }
//    @GetMapping("user-info")
//    public ResponseDto<?> userInfo(@AuthenticationPrincipal UserPrincipal userPrincipal, HttpServletRequest request) {
//        if (userPrincipal == null) {
//            return ResponseDto.error(ErrorCode.ERROR);
//        }
//        String accessToken = extractToken(request);
//
//        return ResponseDto.success(userMapper.toDto(userPrincipal.getUser(), accessToken, null));
//    }
//
//    @Operation(summary = "Email/parol bilan ro'yxatdan o'tish (2 bosqich)", description = "User yaratadi, tasdiqlash kodi emailga yuboriladi")
//    @PostMapping("/sign-up")
//    public ResponseEntity<ResponseDto<Object>> signUp(@Valid @RequestBody SignUpRequest request) {
//        request.setEmail(request.getEmail().trim());
//        request.setUsername(request.getUsername().trim());
//        ResponseDto<Object> response = authService.signUp(request);
//        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
//
//    @Operation(summary = "Sign-up kodi bilan tasdiqlash")
//    @PostMapping("/sign-up/verify")
//    public ResponseEntity<ResponseDto<?>> verifySignUp(@Valid @RequestBody SignUpVerifyRequest request) {
//        ResponseDto<?> response = authService.verifySignUp(request);
//        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
//
//    @Operation(summary = "Email yoki username va parol bilan kirish (kod yuborish bosqichi)")
//    @PostMapping("/sign-in")
//    public ResponseDto<Void> signIn(@Valid @RequestBody LoginForm request) {
//        request.setLogin(request.getLogin().trim());
//        return authService.signIn(request);
//    }
//
//    @Operation(summary = "Kodni tasdiqlab JWT olish")
//    @PostMapping("/sign-in/verify")
//    public ResponseEntity<ResponseDto<TokenResponse>> verifySignIn(@Valid @RequestBody SignInVerifyRequest request, HttpServletRequest http) {
//        ResponseDto<TokenResponse> response = authService.verifySignIn(request);
//        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
//        return ResponseEntity.status(status).body(response);
//    }
//
//    @Operation(summary = "Parolni unutdim – kod yuborish")
//    @PostMapping("/forgot-password")
//    public ResponseEntity<ResponseDto<Object>> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
//        return ResponseEntity.ok(authService.forgotPassword(request));
//    }
//
//
//    @Operation(summary = "Parolni unutdim – kod yuborish")
//    @PostMapping("/reset-password")
//    public ResponseEntity<ResponseDto<Object>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
//        return ResponseEntity.ok(authService.resetPassword(request));
//    }
//}
package com.example.onlinecoursebackend.rest.auth;

import com.example.onlinecoursebackend.dto.ResponseDto;
import com.example.onlinecoursebackend.dto.auth.*;
import com.example.onlinecoursebackend.dto.auth.response.TokenResponse;
import com.example.onlinecoursebackend.dto.enums.ErrorCode;
import com.example.onlinecoursebackend.mapper.UserMapper;
import com.example.onlinecoursebackend.security.UserPrincipal;
import com.example.onlinecoursebackend.service.AuthService;
import com.example.onlinecoursebackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Kayıt, giriş, doğrulama, şifre sıfırlama, token yenileme ve kullanıcı adı kontrolü")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final UserService userService;

    public AuthController(AuthService authService, UserMapper userMapper, UserService userService) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    @Operation(
            summary = "Kullanıcı bilgilerini getir",
            description = "JWT token ile giriş yapmış kullanıcının profil bilgilerini döndürür"
    )
    @GetMapping("user-info")
    public ResponseDto<?> userInfo(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                   HttpServletRequest request) {
        if (userPrincipal == null) {
            return ResponseDto.error(ErrorCode.ERROR);
        }
        String accessToken = extractToken(request);
        return ResponseDto.success(userMapper.toDto(userPrincipal.getUser(), accessToken));
    }

    @Operation(
            summary = "Yeni kullanıcı kaydı (2 adımlı)",
            description = "Kullanıcı oluşturur ve doğrulama kodu e-posta adresine gönderilir"
    )
    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto<Object>> signUp(@Valid @RequestBody SignUpRequest request) {
        request.setEmail(request.getEmail().trim());
        request.setUsername(request.getUsername().trim());
        ResponseDto<Object> response = authService.signUp(request);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @Operation(
            summary = "Kayıt doğrulama",
            description = "E-posta ile gönderilen doğrulama kodu girilir ve hesap aktif hale getirilir"
    )
    @PostMapping("/sign-up/verify")
    public ResponseEntity<ResponseDto<?>> verifySignUp(@Valid @RequestBody SignUpVerifyRequest request) {
        ResponseDto<?> response = authService.verifySignUp(request);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @Operation(
            summary = "Giriş yapma (kod gönderme adımı)",
            description = "E-posta veya kullanıcı adı ile şifre girilir, doğrulama kodu e-postaya gönderilir"
    )
    @PostMapping("/sign-in")
    public ResponseDto<Void> signIn(@Valid @RequestBody LoginForm request) {
        request.setLogin(request.getLogin().trim());
        return authService.signIn(request);
    }

    @Operation(
            summary = "Giriş doğrulama ve JWT alma",
            description = "E-postaya gönderilen kod doğrulanır ve access token ile refresh token döndürülür"
    )
    @PostMapping("/sign-in/verify")
    public ResponseEntity<ResponseDto<TokenResponse>> verifySignIn(@Valid @RequestBody SignInVerifyRequest request,
                                                                   HttpServletRequest http) {
        ResponseDto<TokenResponse> response = authService.verifySignIn(request);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @Operation(
            summary = "Şifremi unuttum – kod gönder",
            description = "Kayıtlı e-posta adresine şifre sıfırlama kodu gönderilir"
    )
    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto<Object>> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(authService.forgotPassword(request));
    }

    @Operation(
            summary = "Şifre sıfırlama",
            description = "E-postaya gönderilen kod ve yeni şifre ile şifre güncellenir"
    )
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto<Object>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(authService.resetPassword(request));
    }
}