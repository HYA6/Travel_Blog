package com.example.TravelProject.blog;

import jakarta.servlet.http.HttpSession;

import com.example.TravelProject.auth.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.TravelProject.auth.UsersDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private BlogService blogService;

    // 블로그 유무 확인
    @GetMapping("/blogChk")
    public String blogChk(HttpSession session) {
        log.info("BlogController의 blogChk() 메소드");
        Long userNum = (Long) session.getAttribute("userNum");
        BlogDto blogDto = blogService.selectBlog(userNum);
        // 블로그가 없으면 생성 메소드 실행
        if (blogDto == null) {
            return "redirect:blog";
        } else {
            // 있으면 카테고리 유무 여부 확인 메소드로 이동
            session.setAttribute("blogId",  blogDto.getBlogId());
            return "redirect:categoryChk";
        }
    }
	
	// 블로그 생성 페이지로 이동
	@GetMapping("/blog")
	public String blog(Model model, HttpSession session) {
		log.info("BlogController의 blog() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");

		// 로그인한 사용자 정보 가져오기
		UsersDto usersDto = usersService.selectIUser(userNum);
		model.addAttribute("userNum", usersDto.getUserNum());
		return "create/blogCreate";
	}
	
	// 블로그 생성
	@PostMapping("/blogCreate")
	public String blogCreate(HttpSession session, BlogDto blogDto) {
		log.info("BlogController의 blogCreate() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");

		// 블로그 생성하기
		blogService.blogCreate(blogDto, userNum);
		// 로그인한 사용자 블로그 선택
		BlogDto dto = blogService.selectBlog(userNum);

		session.setAttribute("blogId", dto.getBlogId());
		return "redirect:category";
	}
	
	// 블로그 수정 페이지로
	@GetMapping("/blogEdit")
	public String blogEdit(HttpSession session, Model model) {
		log.info("BlogController의 blogEdit() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");

        // 로그인한 사용자 정보 가져오기
        UsersDto usersDto = usersService.selectIUser(userNum);
		// 로그인한 사용자 블로그 선택
		BlogDto blogDto = blogService.selectBlog(userNum);

		model.addAttribute("blogDto", blogDto);
		model.addAttribute("usersDto", usersDto);
		return "edit/blogEdit";
	}
	
	// 블로그 수정
	@PostMapping("/blogEditOK")
	public String blogEditOK(BlogDto blogDto) {
		log.info("BlogController의 blogEditOK() 메소드");
		// 블로그 수정하기
		blogService.blogEditOK(blogDto);
		return "redirect:blogEdit";
	}
	
	// 블로그 삭제 후 생성 페이지로 이동
	@PostMapping("/blogDelete")
	public String blogDelete() {
		log.info("BlogController의 blogDelete() 메소드");

		return "redirect:blog";
	}
	
}
