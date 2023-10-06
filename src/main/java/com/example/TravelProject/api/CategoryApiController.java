package com.example.TravelProject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelProject.dto.CategoryDto;
import com.example.TravelProject.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CategoryApiController {
	
	@Autowired
	private CategoryService categoryService;
	
	// 카테고리 생성 및 수정
	@ResponseBody
	@RequestMapping("/api/createCategory")
	public ResponseEntity<Long> createCategory(CategoryDto dto) {
		log.info("CategoryApiController의 createCategory() 메소드 실행");
//		log.info("dto: {}", dto);
		// 카테고리 생성 및 수정하기
		categoryService.createCategory(dto);
		CategoryDto categoryDTO = categoryService.selectCategory(dto);
		return ResponseEntity.status(HttpStatus.OK).body(categoryDTO.getCategoryId());
	};
	
	// 카테고리 옵션들을 통해 카테고리 불러오기
	@ResponseBody
	@RequestMapping("/api/selectCategory")
	public ResponseEntity<CategoryDto> selectCategory(CategoryDto dto) {
		log.info("CategoryApiController의 selectCategoryById() 메소드 실행");
//		log.info("dto: {}", dto);
		// 카테고리 불러오기
		CategoryDto categoryDTO = categoryService.selectCategory(dto);
		return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
	};
	
	// 카테고리 고유 번호를 통해 카테고리 불러오기
	@ResponseBody
	@RequestMapping("/api/selectCategoryById")
	public ResponseEntity<CategoryDto> selectCategoryById(CategoryDto dto) {
		log.info("CategoryApiController의 selectCategoryById() 메소드 실행");
//		log.info("dto: {}", dto);
		// 카테고리 불러오기
		CategoryDto categoryDTO = categoryService.selectCategoryById(dto);
		return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
	};
	
	// 카테고리 삭제하기
	@ResponseBody
	@RequestMapping("/api/deleteCategory")
	public ResponseEntity<String> deleteCategory(CategoryDto dto) {
		log.info("CategoryApiController의 deleteCategory() 메소드 실행");
//		log.info("dto: {}", dto);
		// 카테고리 삭제하기
		categoryService.deleteCategory(dto);
		return ResponseEntity.status(HttpStatus.OK).body("삭제 성공");
	};
	
};
