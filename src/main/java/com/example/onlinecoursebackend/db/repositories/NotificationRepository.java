package com.example.onlinecoursebackend.db.repositories;

import com.example.onlinecoursebackend.db.entity.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
