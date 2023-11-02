package com.example.TravelProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
	
	@Query(value="SELECT * FROM comments WHERE post_id = :post_id ORDER BY comment_gup, comment_seq",
			nativeQuery = true)
	List<Comments> findCommentsByPostId(Long post_id);
	
	@Query(value="SELECT COUNT(*) FROM comments WHERE post_id = :post_id", nativeQuery = true)
	int selectAllComments(Long post_id);

	@Query(value="SELECT * FROM comments WHERE blog_id = :blog_id ORDER BY comment_id DESC Limit 0, 5",
			nativeQuery = true)
	List<Comments> findCommentsByBlogId(Long blog_id);
	
};
