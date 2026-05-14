package com.example.onlinecoursebackend.db.entity.chat;

import com.example.onlinecoursebackend.db.entity.enums.FileType;
import com.example.onlinecoursebackend.db.entity.enums.MessageStatus;
import com.example.onlinecoursebackend.db.entity.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(columnDefinition = "TEXT")
    private String content;                     // Xabar matni (fayl yuborilsa null bo'lishi mumkin)

    // Fayl ma'lumotlari
    private String fileUrl;                     // Fayl URL si
    private String fileName;                    // Asl fayl nomi

    @Enumerated(EnumType.STRING)
    private FileType fileType;                  // Fayl turi

    private Long fileSize;                      // Fayl hajmi (byte)
    private Long duration;                      // Audio/Video davomiyligi (soniyada)

    @Enumerated(EnumType.STRING)
    private MessageStatus status = MessageStatus.SENT;

    private Boolean edited = false;
    private Boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    // ========================
    //       Getters/Setters
    // ========================

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Chat getChat() { return chat; }
    public void setChat(Chat chat) { this.chat = chat; }

    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public FileType getFileType() { return fileType; }
    public void setFileType(FileType fileType) { this.fileType = fileType; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public Long getDuration() { return duration; }
    public void setDuration(Long duration) { this.duration = duration; }

    public MessageStatus getStatus() { return status; }
    public void setStatus(MessageStatus status) { this.status = status; }

    public Boolean getEdited() { return edited; }
    public void setEdited(Boolean edited) { this.edited = edited; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}