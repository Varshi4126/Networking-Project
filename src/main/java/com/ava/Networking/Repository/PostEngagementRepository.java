////package com.ava.Networking.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.ava.Networking.Model.PostEngagement;
//
//@Repository
//public interface PostEngagementRepository extends JpaRepository<PostEngagement, Long> {
//    List<PostEngagement> findByPostId(Long postId);
//    List<PostEngagement> findByPostIdAndType(Long postId, PostEngagement.EngagementType type);
//    Optional<PostEngagement> findByPostIdAndUserIdAndType(Long postId, Long userId, PostEngagement.EngagementType type);
//}