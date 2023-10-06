package com.example.TravelProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TravelProject.dto.CategoryDto;
import com.example.TravelProject.entity.Blog;
import com.example.TravelProject.entity.Category;
import com.example.TravelProject.repository.BlogRepository;
import com.example.TravelProject.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {
	
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	// 카테고리 생성 및 수정
	@Transactional
	public void createCategory(CategoryDto dto) {
		log.info("CategoryService의 createCategory() 메소드 실행");
		// 카테고리를 저장하려는 블로그가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Blog blog = blogRepository.findById(dto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("카테고리 생성 실패! 대상 블로그가 없습니다."));
//		log.info("category: {}", category);
		if (dto.getCategoryId() == null) {
			// dto를 entity로 변환
			Category category = Category.toEntity(dto, blog);
			// 카테고리 저장
			categoryRepository.save(category);
		} else {
			// 카테고리 저장
			Category category = categoryRepository.findById(dto.getCategoryId())
					.orElseThrow(() -> new IllegalArgumentException("카테고리 수정 실패! 대상 카테고리가 없습니다."));
			category.update(dto);
			categoryRepository.save(category);
		};
	};
	
	// 블로그 고유 번호로 카테고리 목록 찾기
	@Transactional
	public List<CategoryDto> selectCategoryList(Long blogId) {
		log.info("CategoryService의 selectCategoryList() 메소드 실행");
		// stream 사용
		return categoryRepository.selectByBlog(blogId)
				.stream()
				.map(category -> CategoryDto.toDto(category)) // entity를 dto로 변환
				.collect(Collectors.toList());
	};
	
	// 카테고리 옵션들로 카테고리 한 건 찾기
	@Transactional
	public CategoryDto selectCategory(CategoryDto dto) {
		log.info("CategoryService의 selectCategoryById() 메소드 실행");
		// 카테고리를 찾으려는 블로그가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Blog blog = blogRepository.findById(dto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("카테고리 찾기 실패! 대상 블로그가 없습니다."));
		// dto를 entity로 변환
		Category entity = Category.toEntity(dto, blog);
		// 카테고리 찾기
		Category category = categoryRepository.selectByOption(entity.getCategoryGup(), entity.getCategoryLev(), 
					entity.getCategorySeq(), entity.getBlog().getBlogId());
//		log.info("category: {}", category);
		return category != null ? CategoryDto.toDto(category) : null;
	};
	
	// 카테고리 고유 번호로 카테고리 한 건 찾기
	@Transactional
	public CategoryDto selectCategoryById(CategoryDto dto) {
		log.info("CategoryService의 selectCategoryById() 메소드 실행");
		// 카테고리를 찾으려는 블로그가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Blog blog = blogRepository.findById(dto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("카테고리 찾기 실패! 대상 블로그가 없습니다."));
		// dto를 entity로 변환
		Category entity = Category.toEntity(dto, blog);
		// 카테고리 찾기
		Category category = categoryRepository.findById(entity.getCategoryId()).orElse(null);
//		log.info("category: {}", category);
		return CategoryDto.toDto(category);
	};
	
	// 카테고리 삭제
	@Transactional
	public void deleteCategory(CategoryDto dto) {
		log.info("CategoryService의 deleteCategory() 메소드 실행");
		// 카테고리를 찾으려는 블로그가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Blog blog = blogRepository.findById(dto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("카테고리 찾기 실패! 대상 블로그가 없습니다."));
		// dto를 entity로 변환
		Category entity = Category.toEntity(dto, blog);
		log.info("{}", entity.getCategoryId());
		if (entity.getCategoryId() == null || entity.getCategoryId() == 0) {
			// 메인 카테고리면
			categoryRepository.deleteMain(entity.getCategoryGup());
		} else {
			// 서브 카테고리면
			categoryRepository.deleteById(entity.getCategoryId());
		};
	};

};