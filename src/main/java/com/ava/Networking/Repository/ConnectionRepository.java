package com.ava.Networking.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ava.Networking.Model.Connection;
import com.ava.Networking.Model.Connection.ConnectionStatus;

@Repository
public class ConnectionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createOrUpdateConnection(Long initiatorUserId, Long targetUserId, ConnectionStatus status) {
        String sql = "MERGE INTO connections AS target " +
                     "USING (VALUES (?, ?, ?)) AS source (initiator_user_id, target_user_id, status) " +
                     "ON target.initiator_user_id = source.initiator_user_id AND target.target_user_id = source.target_user_id " +
                     "WHEN MATCHED THEN UPDATE SET status = source.status " +
                     "WHEN NOT MATCHED THEN INSERT (initiator_user_id, target_user_id, status) " +
                     "VALUES (source.initiator_user_id, source.target_user_id, source.status)";
        jdbcTemplate.update(sql, initiatorUserId, targetUserId, status.name());
    }

    public List<Connection> getConnectionsForUser(Long userId) {
        String sql = "SELECT * FROM connections WHERE initiator_user_id = ? OR target_user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId, userId}, (rs, rowNum) -> {
            Connection connection = new Connection();
            connection.setConnectionId(rs.getLong("connection_id"));
            connection.setInitiatorUserId(rs.getLong("initiator_user_id"));
            connection.setTargetUserId(rs.getLong("target_user_id"));
            connection.setStatus(ConnectionStatus.valueOf(rs.getString("status")));
            connection.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            connection.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return connection;
        });
    }

    public void updateConnectionStatus(Long initiatorUserId, Long targetUserId, ConnectionStatus status) {
        String sql = "UPDATE connections SET status = ? WHERE (initiator_user_id = ? AND target_user_id = ?) OR (initiator_user_id = ? AND target_user_id = ?)";
        jdbcTemplate.update(sql, status.name(), initiatorUserId, targetUserId, targetUserId, initiatorUserId);
    }

    public Connection getConnectionByUsers(Long userId1, Long userId2) {
        String sql = "SELECT * FROM connections WHERE (initiator_user_id = ? AND target_user_id = ?) OR (initiator_user_id = ? AND target_user_id = ?)";
        List<Connection> connections = jdbcTemplate.query(sql, new Object[]{userId1, userId2, userId2, userId1}, (rs, rowNum) -> {
            Connection connection = new Connection();
            connection.setConnectionId(rs.getLong("connection_id"));
            connection.setInitiatorUserId(rs.getLong("initiator_user_id"));
            connection.setTargetUserId(rs.getLong("target_user_id"));
            connection.setStatus(ConnectionStatus.valueOf(rs.getString("status")));
            connection.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            connection.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return connection;
        });
        return connections.isEmpty() ? null : connections.get(0);
    }
}