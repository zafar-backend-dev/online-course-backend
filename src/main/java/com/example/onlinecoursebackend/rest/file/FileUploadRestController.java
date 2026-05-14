package com.example.onlinecoursebackend.rest.file;

import com.example.onlinecoursebackend.dto.ResponseDto;
import com.example.onlinecoursebackend.dto.enums.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/file")
@Tag(name = "Dosya", description = "Dosya yükleme ve silme işlemleri")
public class FileUploadRestController {

    @Value("${file.upload.dir}")
    private String uploadDirectory;


    @Value("${file.upload.max-size:104857600}") // 100MB
    private Long maxFileSize;

    private static final String[] ALLOWED_EXTENSIONS = {
            // Rasmlar
            "jpg", "jpeg", "png", "gif", "webp", "svg", "bmp",
            // Videolar
            "mp4", "mkv", "avi", "mov", "wmv", "flv", "webm",
            "3gp", "3g2", "mpeg", "mpg", "ts", "mts", "m2ts", "m4v",
            // Audiolar
            "mp3", "wav", "ogg", "aac", "flac", "m4a", "wma", "opus",
            // Hujjatlar
            "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "csv"
    };

    @Operation(
            summary = "Dosya yükle",
            description = "Sunucuya dosya yükler ve erişim URL'sini döndürür"
    )
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<FileUploadResponseDto> upload(@RequestParam("file") MultipartFile file) {
        try {
            // Fayl bo'shligini tekshirish
            if (file.isEmpty()) {
                return ResponseDto.error(ErrorCode.FILE_IS_EMPTY);
            }

            // Fayl hajmini tekshirish
            if (file.getSize() > maxFileSize) {
                return ResponseDto.error(ErrorCode.FILE_TOO_LARGE);
            }

            // Fayl turini tekshirish
            String originalFilename = file.getOriginalFilename();
            if (!isAllowedFileType(originalFilename)) {
                return ResponseDto.error(ErrorCode.INVALID_FILE_TYPE);
            }

            // Upload papkasini yaratish
            Path uploadPath = Paths.get(uploadDirectory);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Xavfsiz fayl nomini yaratish
            String extension = getFileExtension(originalFilename);
            String safeFileName = UUID.randomUUID().toString().replace("-", "")
                    + (extension.isEmpty() ? "" : "." + extension);

            // Faylni saqlash
            Path filePath = uploadPath.resolve(safeFileName);
            Files.copy(file.getInputStream(), filePath);

            // Response qaytarish
            FileUploadResponseDto response = new FileUploadResponseDto();
            response.setOriginalFileName(originalFilename);
            response.setSavedFileName(safeFileName);
            response.setFileUrl("/uploads/" + safeFileName);
            response.setFileSize(file.getSize());
            response.setContentType(file.getContentType());

            return ResponseDto.success(response);

        } catch (IOException e) {
            return ResponseDto.error(ErrorCode.FILE_UPLOAD_ERROR);
        }
    }

    @Operation(
            summary = "Dosya sil",
            description = "Sunucudan belirtilen dosyayı siler"
    )
    @DeleteMapping("/delete/{fileName}")
    public ResponseDto<Boolean> deleteFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDirectory, fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return ResponseDto.success(true);
            }
            return ResponseDto.error(ErrorCode.FILE_NOT_FOUND);
        } catch (IOException e) {
            return ResponseDto.error(ErrorCode.FILE_DELETE_ERROR);
        }
    }

    private boolean isAllowedFileType(String filename) {
//        if (filename == null) return false;
//        String extension = getFileExtension(filename).toLowerCase();
//        if (extension.isEmpty()) return false;
//        for (String allowed : ALLOWED_EXTENSIONS) {
//            if (allowed.equals(extension)) return true;
//        }
//        return false;
        return true;
    }

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "";
        return filename.substring(filename.lastIndexOf(".") + 1);
    }



    public static class FileUploadResponseDto {
        private String fileUrl;
        private String originalFileName;
        private String savedFileName;
        private Long fileSize;
        private String contentType;

        public String getFileUrl() { return fileUrl; }
        public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

        public String getOriginalFileName() { return originalFileName; }
        public void setOriginalFileName(String originalFileName) { this.originalFileName = originalFileName; }

        public String getSavedFileName() { return savedFileName; }
        public void setSavedFileName(String savedFileName) { this.savedFileName = savedFileName; }

        public Long getFileSize() { return fileSize; }
        public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

        public String getContentType() { return contentType; }
        public void setContentType(String contentType) { this.contentType = contentType; }
    }
}