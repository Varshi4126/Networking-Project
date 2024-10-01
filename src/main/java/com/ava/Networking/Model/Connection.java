package com.ava.Networking.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long connectionId;

    @ManyToOne
    @JoinColumn(name = "initiator_user_id")
    private User initiator;

    @ManyToOne
    @JoinColumn(name = "target_user_id")
    private User target;

    @Enumerated(EnumType.STRING)
    private ConnectionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum ConnectionStatus {
        PENDING, CONNECTED, FOLLOWING
    }

    // Getters and setters

    public Long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

	public void setInitiatorUserId(long id) {
		// TODO Auto-generated method stub
		this.initiator.setUserId(id);
	}

	public void setTargetUserId(long id) {
		// TODO Auto-generated method stub
		this.target.setUserId(id);
	}
	
}