package com.example.TravelProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.PostImages;

public interface PostImagesRepository extends JpaRepository<PostImages, Long> {
	
	@Query(value="SELECT * FROM POST_IMAGES "
			+ "WHERE post_id = :post_id "
			+ "ORDER BY image_gup, post_seq", nativeQuery = true)
	List<PostImages> findByPostId(Long post_id);

};
