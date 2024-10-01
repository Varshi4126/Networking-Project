package com.ava.Networking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ava.Networking.Model.Notification;
import com.ava.Networking.Repository.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(Long userId, String message) {
        notificationRepository.createNotification(userId, message);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.getUnreadNotifications(userId);
    }

    public void markAsRead(Long notificationId) {
        notificationRepository.markAsRead(notificationId);
    }
}