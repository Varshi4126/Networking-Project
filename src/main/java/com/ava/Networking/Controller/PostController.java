package com.ava.Networking.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ava.Networking.Model.Post;
import com.ava.Networking.Model.User;
import com.ava.Networking.Service.PostService;
import com.ava.Networking.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private PostService postService;

    @PostMapping
    public Post createPost(@RequestParam String username,
                           @RequestParam String content,
                           @RequestParam(required = false) String mediaUrl) {
        logger.info(username);
        User author = userService.findByUsername(username);
        
        if (author == null) {
            throw new RuntimeException("User not found: " + username);
        }
        
        return postService.createPost(author, content, mediaUrl);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Post>> getRecentPosts() {
        List<Post> recentPosts = postService.getRecentPosts();
        return ResponseEntity.ok(recentPosts);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Post>> getRecentPostsByUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Post> userPosts = postService.getRecentPostsByUser(user);
        return ResponseEntity.ok(userPosts);
    }
    
}