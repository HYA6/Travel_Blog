package com.example.TravelProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TravelProject.dto.BlogDto;
import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.entity.Users;
import com.example.TravelProject.repository.UsersRepository;
import com.example.TravelProject.service.BlogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogController {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private BlogService blogService;
	
	// 블로그 생성 페이지로 이동
	@RequestMapping("/blog")
	public String blog(Model model, HttpSession session) {
		log.info("BlogController의 blog() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");
//		log.info("userNum: {}", userNum);
		// 로그인한 사용자 정보 가져오기
		Users users = usersRepository.findById(userNum).orElse(null);
//		log.info("users: {}", users);
		UsersDto usersDto = UsersDto.toDto(users);
		model.addAttribute("userNum", usersDto.getUserNum());
		return "create/blogCreate";
	};
	
	// 블로그 생성
	@RequestMapping("/blogCreate")
	public String blogCreate(HttpSession session, BlogDto blogDto, UsersDto usersDto) {
		log.info("BlogController의 blogCreate() 메소드");
//		log.info("blogDto: {}", blogDto);
		Long userNum = usersDto.getUserNum();
		// 블로그 생성하기
		blogService.blogCreate(blogDto, userNum);
		// 로그인한 사용자 블로그 선택
		BlogDto dto = blogService.selectBlog(userNum);
//		log.info("dto: {}", dto);
		session.setAttribute("blogId", dto.getBlogId());
		return "redirect:category";
	};
	
	// 블로그 설정 페이지로
	@RequestMapping("/blogEdit")
	public String blogEdit(HttpSession session, Model model) {
		log.info("BlogController의 blogEdit() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");
//		log.info("userNum: {}", userNum);
		// 로그인한 사용자 정보 가져오기
		Users users = usersRepository.findById(userNum).orElse(null);
//		log.info("users: {}", users);
		UsersDto usersDto = UsersDto.toDto(users);
		// 로그인한 사용자 블로그 선택
		BlogDto dto = blogService.selectBlog(userNum);
//		log.info("dto: {}", dto);
		model.addAttribute("blogDto", dto);
		model.addAttribute("usersDto", usersDto);
		return "edit/blogEdit";
	}
	
	// 블로그 수정
	@RequestMapping("/blogEditOK")
	public String blogEditOK(HttpSession session, BlogDto blogDto) {
		log.info("BlogController의 blogEditOK() 메소드");
		// 블로그 수정하기
		blogService.blogEditOK(blogDto);
		
		session.setAttribute("userNum", blogDto.getUserNum());
		return "redirect:blogEdit";
	}
	
	// 블로그 삭제 후 생성 페이지로 이동
	@RequestMapping("/blogDelete")
	public String blogDelete(HttpSession session) {
		log.info("BlogController의 blogDelete() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");
//		log.info("userNum: {}", userNum);
		session.setAttribute("userNum", userNum);
		return "redirect:blog";
	}
	
}
