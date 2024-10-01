package com.ava.Networking.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;
import com.ava.Networking.Model.Notification;

public class NotificationRowMapper implements RowMapper<Notification> {
    @Override
    public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
        Notification notification = new Notification();
        notification.setNotificationId(rs.getLong("notification_id"));
        notification.setReceiverId(rs.getLong("receiver_id"));
        notification.setMessage(rs.getString("message"));
        notification.setRead(rs.getBoolean("is_read"));
        notification.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        return notification;
    }
}