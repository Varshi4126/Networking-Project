//package com.ava.Networking.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ava.Networking.Model.Post;
//import com.ava.Networking.Model.PostEngagement;
//import com.ava.Networking.Model.User;
//import com.ava.Networking.Repository.PostEngagementRepository;
//
//@Service
//public class PostEngagementService {
//
//    @Autowired
//    private PostEngagementRepository postEngagementRepository;
//
//    public PostEngagement addLike(Post post, User user) {
//        Optional<PostEngagement> existingLike = postEngagementRepository.findByPostIdAndUserIdAndType(post.getId(), user.getUserId(), PostEngagement.EngagementType.LIKE);
//        if (existingLike.isPresent()) {
//            return existingLike.get();
//        }
//
//        PostEngagement like = new PostEngagement();
//        like.setPost(post);
//        like.setUser(user);
//        like.setType(PostEngagement.EngagementType.LIKE);
//        return postEngagementRepository.save(like);
//    }
//
//    public PostEngagement addComment(Post post, User user, String comment) {
//        PostEngagement postComment = new PostEngagement();
//        postComment.setPost(post);
//        postComment.setUser(user);
//        postComment.setType(PostEngagement.EngagementType.COMMENT);
//        postComment.setComment(comment);
//        return postEngagementRepository.save(postComment);
//    }
//
//    public List<PostEngagement> getEngagementsByPostId(Long postId) {
//        return postEngagementRepository.findByPostId(postId);
//    }
//
//    public long getLikeCount(Long postId) {
//        return postEngagementRepository.findByPostIdAndType(postId, PostEngagement.EngagementType.LIKE).size();
//    }
//
//    public List<PostEngagement> getComments(Long postId) {
//        return postEngagementRepository.findByPostIdAndType(postId, PostEngagement.EngagementType.COMMENT);
//    }
//}