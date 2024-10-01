//package com.ava.Networking.Model;
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "PostTags")
//public class PostTag {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long tagId;
//
//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;
//
//    @ManyToOne
//    @JoinColumn(name = "tagged_user_id")
//    private User taggedUser;
//
//    private LocalDateTime createdAt;
//
//	public Long getTagId() {
//		return tagId;
//	}
//
//	public void setTagId(Long tagId) {
//		this.tagId = tagId;
//	}
//
//	public Post getPost() {
//		return post;
//	}
//
//	public void setPost(Post post) {
//		this.post = post;
//	}
//
//	public User getTaggedUser() {
//		return taggedUser;
//	}
//
//	public void setTaggedUser(User taggedUser) {
//		this.taggedUser = taggedUser;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//    
//}
