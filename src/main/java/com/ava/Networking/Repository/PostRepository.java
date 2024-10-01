package com.ava.Networking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ava.Networking.Model.Post;
import com.ava.Networking.Model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByAuthorOrderByCreatedAtDesc(User author);
}