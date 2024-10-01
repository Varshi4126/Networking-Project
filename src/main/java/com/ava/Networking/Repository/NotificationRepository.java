package com.ava.Networking.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ava.Networking.Model.Notification;

@Repository
public class NotificationRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createNotification(Long userId, String message) {
        String sql = "INSERT INTO notifications (user_id, message) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, message);
    }

    public List<Notification> getUnreadNotifications(Long userId) {
        String sql = "SELECT * FROM notifications WHERE user_id = ? AND is_read = 0";
        return jdbcTemplate.query(sql, new Object[]{userId}, new NotificationRowMapper());
    }

    public void markAsRead(Long notificationId) {
        String sql = "UPDATE notifications SET is_read = 1 WHERE notification_id = ?";
        jdbcTemplate.update(sql, notificationId);
    }
}