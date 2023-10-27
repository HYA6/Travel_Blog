package com.example.TravelProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.PostVisit;

public interface PostVisitRepository extends JpaRepository<PostVisit, Long> {
	
	@Query(value = "SELECT visit_date FROM post_visit WHERE post_id = :post_id AND user_num = :user_num", nativeQuery = true)
	String findDate(Long user_num, Long post_id);
	
	@Query(value="SELECT COUNT(*) FROM post_visit WHERE post_id = :post_id", nativeQuery = true)
	int findByPostId(Long post_id);

};
