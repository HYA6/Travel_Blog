package com.example.TravelProject.post;

import java.util.List;

import com.example.TravelProject.post.entity.PostTexts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostTextsRepository extends JpaRepository<PostTexts, Long> {
	
	@Query(value="SELECT * FROM POST_TEXTS "
			+ "WHERE post_id = :post_id "
			+ "ORDER BY text_gup, text_seq", nativeQuery = true)
	List<PostTexts> findByPostId(Long post_id);

};
