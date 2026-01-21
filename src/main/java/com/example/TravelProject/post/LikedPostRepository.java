package com.example.TravelProject.post;

import com.example.TravelProject.post.entity.LikedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikedPostRepository extends JpaRepository<LikedPost, Long> {
	
	@Query(value="SELECT COUNT(*) FROM liked_post WHERE post_id = :post_id", nativeQuery=true)
	int selectAllLikes(Long post_id);
	
	@Query(value="SELECT * FROM liked_post WHERE post_id = :post_id AND user_num = :user_num", nativeQuery=true)
	LikedPost findLiked(Long user_num, Long post_id);

};
