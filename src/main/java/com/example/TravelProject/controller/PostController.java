package com.example.TravelProject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TravelProject.dto.CategoryDto;
import com.example.TravelProject.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PostController {
	
	@Autowired
	private CategoryService categoryService;
	
	// 게시글 작성 페이지로 이동
	@RequestMapping("/writePost")
	public String writePost(Model model, HttpSession session) {
		log.info("PostController의 writePost() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");
//		log.info("userNum: {}", userNum);
		Long blogId = (Long) session.getAttribute("blogId");
//		log.info("blogId: {}", blogId);
		
		// 블로그에 있는 카테고리 전부 가져오기
		List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogId);
		log.info("categoryDto: {}", categoryDto);
		
		model.addAttribute("categoryDto", categoryDto);
		model.addAttribute("userNum", userNum);
		return "create/postCreate";
	};
	
};
