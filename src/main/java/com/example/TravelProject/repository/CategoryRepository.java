package com.example.TravelProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.TravelProject.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	// 카테고리 목록 찾기
	@Query(value = "SELECT * FROM category WHERE blog_id = :blogId ORDER BY category_gup, category_lev, category_seq", nativeQuery = true)
	List<Category> selectByBlog(Long blogId);
	
	// 카테고리 찾기
	@Query(value = "SELECT * FROM category WHERE blog_id = :blog_id AND category_gup = :category_gup AND category_lev = :category_lev AND category_seq = :category_seq", nativeQuery = true)
	Category selectByOption(int category_gup, int category_lev, int category_seq, Long blog_id);
	
	// 메인 카테고리 + 메인에 따른 서브 카테고리 삭제
	@Modifying
	@Query(value = "DELETE FROM category WHERE category_gup = :category_gup", nativeQuery = true)
	void deleteMain(int category_gup);
	
};
