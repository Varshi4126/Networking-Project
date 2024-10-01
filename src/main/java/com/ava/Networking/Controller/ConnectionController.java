package com.ava.Networking.Controller;

import com.ava.Networking.Model.Connection;
import com.ava.Networking.Service.ConnectionService;
import com.ava.Networking.Service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connection")
public class ConnectionController {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionController.class);

    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/connect")
    public ResponseEntity<?> connect(@RequestParam Long initiatorUserId, @RequestParam Long targetUserId) {
        try {
            connectionService.requestConnection(initiatorUserId, targetUserId);
            notificationService.createNotification(targetUserId, "You have a new connection request from user " + initiatorUserId);
            return ResponseEntity.ok("Connection request sent");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@RequestParam Long followerId, @RequestParam Long followedId) {
        try {
            connectionService.followUser(followerId, followedId);
            notificationService.createNotification(followedId, "User " + followerId + " is now following you");
            return ResponseEntity.ok("Now following");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @PostMapping("/accept")
    public ResponseEntity<?> acceptConnection(@RequestParam Long initiatorUserId, @RequestParam Long targetUserId) {
        try {
            connectionService.acceptConnection(initiatorUserId, targetUserId);
            notificationService.createNotification(initiatorUserId, "Your connection request has been accepted");
            logger.info("Connection accepted between users {} and {}", initiatorUserId, targetUserId);
            return ResponseEntity.ok("Connection accepted");
        } catch (Exception e) {
            logger.error("Error in acceptConnection method", e);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> getConnectionStatus(@RequestParam Long userId1, @RequestParam Long userId2) {
        try {
            Connection.ConnectionStatus status = connectionService.getConnectionStatus(userId1, userId2);
            logger.info("Connection status between users {} and {} is {}", userId1, userId2, status);
            return ResponseEntity.ok(status != null ? status.name() : "NOT_CONNECTED");
        } catch (Exception e) {
            logger.error("Error in getConnectionStatus method", e);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/is-following")
    public ResponseEntity<?> isFollowing(@RequestParam Long followerId, @RequestParam Long followedId) {
        try {
            boolean isFollowing = connectionService.isFollowing(followerId, followedId);
            logger.info("User {} following user {}: {}", followerId, followedId, isFollowing);
            return ResponseEntity.ok(isFollowing);
        } catch (Exception e) {
            logger.error("Error in isFollowing method", e);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}