package com.example.TravelProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	// 유저 고유 번호에 맞는 블로그 찾기
	@Query(value = "SELECT * FROM blog WHERE user_num = :userNum", nativeQuery = true)
	Blog findByusersId(Long userNum);
	
	// 유저 고유 번호에 맞는 블로그 찾기
	@Query(value = "SELECT blog_url FROM blog WHERE blog_url = :blogUrl", nativeQuery = true)
	String findByUrl(String blogUrl);
	
}
