package com.example.TravelProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query(value="SELECT * FROM post WHERE post_subject = :post_subject AND post_strat_date = :post_strat_date AND "
			+ "post_end_date = :post_end_date AND blog_id = :blog_id AND category_id = :category_id", nativeQuery = true)
	Post selectPostByOption(String post_subject, String post_strat_date, String post_end_date, Long blog_id, Long category_id);

};
