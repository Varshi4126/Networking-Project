package com.ava.Networking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ava.Networking.Repository.ConnectionRepository;
import com.ava.Networking.Model.Connection;
import com.ava.Networking.Model.Connection.ConnectionStatus;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    public void requestConnection(Long initiatorUserId, Long targetUserId) {
        Connection existingConnection = connectionRepository.getConnectionByUsers(initiatorUserId, targetUserId);
        if (existingConnection == null) {
            connectionRepository.createOrUpdateConnection(initiatorUserId, targetUserId, ConnectionStatus.PENDING);
        } else if (existingConnection.getStatus() == ConnectionStatus.FOLLOWING) {
            connectionRepository.updateConnectionStatus(initiatorUserId, targetUserId, ConnectionStatus.PENDING);
        }
    }

    public void followUser(Long followerId, Long followedId) {
        Connection existingConnection = connectionRepository.getConnectionByUsers(followerId, followedId);
        if (existingConnection == null || existingConnection.getStatus() != ConnectionStatus.CONNECTED) {
            connectionRepository.createOrUpdateConnection(followerId, followedId, ConnectionStatus.FOLLOWING);
        }
    }

    public void acceptConnection(Long initiatorUserId, Long targetUserId) {
        connectionRepository.updateConnectionStatus(initiatorUserId, targetUserId, ConnectionStatus.CONNECTED);
        connectionRepository.createOrUpdateConnection(targetUserId, initiatorUserId, ConnectionStatus.CONNECTED);
    }


    public ConnectionStatus getConnectionStatus(Long userId1, Long userId2) {
        Connection connection = connectionRepository.getConnectionByUsers(userId1, userId2);
        return connection != null ? connection.getStatus() : null;
    }

    public boolean isFollowing(Long followerId, Long followedId) {
        Connection connection = connectionRepository.getConnectionByUsers(followerId, followedId);
        return connection != null && connection.getStatus() == ConnectionStatus.FOLLOWING;
    }
}