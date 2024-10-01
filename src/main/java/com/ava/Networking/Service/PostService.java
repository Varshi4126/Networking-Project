package com.ava.Networking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ava.Networking.Model.Post;
import com.ava.Networking.Model.User;
import com.ava.Networking.Repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(User author, String content, String mediaUrl) {
        Post post = new Post();
        post.setAuthor(author);
        post.setContent(content);
        post.setMediaUrl(mediaUrl);
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public List<Post> getRecentPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Post> getRecentPostsByUser(User user) {
        return postRepository.findByAuthorOrderByCreatedAtDesc(user);
    }
    
}