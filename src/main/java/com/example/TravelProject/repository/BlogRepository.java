package com.example.TravelProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	// 유저 고유 번호에 맞는 블로그 찾기
	@Query(value = "SELECT * FROM blog WHERE user_num = :user_num", nativeQuery = true)
	Blog findByusersId(Long user_num);
	
	// 유저 고유 번호에 맞는 블로그 찾기
	@Query(value = "SELECT blog_url FROM blog WHERE blog_url = :blog_url", nativeQuery = true)
	String findByUrl(String blog_url);
	
}
