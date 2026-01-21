package com.example.TravelProject.post;

import java.util.List;

import com.example.TravelProject.post.entity.PostImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostImagesRepository extends JpaRepository<PostImages, Long> {
	
	@Query(value="SELECT * FROM POST_IMAGES "
			+ "WHERE post_id = :post_id "
			+ "ORDER BY image_gup, image_seq", nativeQuery = true)
	List<PostImages> findByPostId(Long post_id);

};
