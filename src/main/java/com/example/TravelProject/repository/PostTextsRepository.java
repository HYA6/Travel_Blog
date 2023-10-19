package com.example.TravelProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.PostTexts;

public interface PostTextsRepository extends JpaRepository<PostTexts, Long> {
	
	@Query(value="SELECT * FROM post_contents "
			+ "WHERE post_id = :post_id "
			+ "ORDER BY content_gup, content_seq", nativeQuery = true)
	List<PostTexts> findByPostId(Long post_id);

};