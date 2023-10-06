package com.example.TravelProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TravelProject.dto.BlogDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CategoryController {
	
	// 카테고리 생성 페이지로 이동
	@RequestMapping("/category")
	public String category(Model model, HttpSession session) {
		log.info("CategoryController의 category() 메소드");
		
		Long userNum = (Long) session.getAttribute("userNum");
//		log.info("userNum: {}", userNum);
		Long blogId = (Long) session.getAttribute("blogId");
//		log.info("blogId: {}", blogId);
		
		model.addAttribute("userNum", userNum);
		model.addAttribute("blogId", blogId);
		return "create/categoryCreate";
	};
	
	// 메인 페이지로 이동
	@RequestMapping("/categoryToMain")
	public String categoryToMain(BlogDto blogDto, HttpSession session) {
		log.info("CategoryController의 categoryToMain() 메소드");
//		log.info("blogDto: {}", blogDto);
		
		Long blogId = blogDto.getBlogId();
		Long userNum = blogDto.getUserNum();
		
		// 메인 페이지로 이동
		session.setAttribute("userNum", userNum);
		session.setAttribute("blogId", blogId);
		return "redirect:main";
	};
	
};
