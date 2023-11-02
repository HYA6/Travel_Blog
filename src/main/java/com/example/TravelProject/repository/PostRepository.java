package com.example.TravelProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query(value="SELECT * FROM post WHERE post_subject = :post_subject AND post_start_date = :post_start_date AND "
			+ "post_end_date = :post_end_date AND blog_id = :blog_id AND category_id = :category_id", nativeQuery = true)
	Post selectPostByOption(String post_subject, String post_start_date, String post_end_date, Long blog_id, Long category_id);
	
	@Query(value="SELECT * FROM post WHERE blog_id = :blog_id", nativeQuery = true)
	List<Post> selectByBlog(Long blog_id);
	
	@Query(value="SELECT * FROM post WHERE blog_id = :blog_id ORDER BY post_likes DESC, post_id Limit 0, 5", nativeQuery = true)
	List<Post> findPopularPost(Long blog_id);

};
