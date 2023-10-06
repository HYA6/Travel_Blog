package com.example.TravelProject.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TravelProject.dto.BlogDto;
import com.example.TravelProject.dto.CategoryDto;
import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.loginApi.NaverLoginBO;
import com.example.TravelProject.service.BlogService;
import com.example.TravelProject.service.CategoryService;
import com.example.TravelProject.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	// NaverLoginBO
	@Autowired
	private NaverLoginBO naverLoginBO;
	@Autowired
	private UsersService usersService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	// 첫 실행
	@GetMapping("/")
	public String login(Model model, HttpSession session) throws UnsupportedEncodingException {
		log.info("LoginController의 login() 실행");
		
		// 네이버 아이디로 인증 URL을 생성하기 위해서 NaverLoginBO 클래스의 getAuthorizationUrl() 메소드를
		// 호출한다.
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
//		log.info("네이버: {}", naverAuthUrl);
		
		model.addAttribute("NaverLoginUrl", naverAuthUrl);
	    
		return "create/login";
	}
	
	// 로그인 가능 여부 확인
	@RequestMapping("/usersSignup")
	public String usersSignup(UsersDto usersDto, RedirectAttributes rttr, HttpSession session) {
		log.info("LoginController의 usersSignup() 메소드 실행");
		
		// 이미 있는 유저 고유 번호인지 확인
		UsersDto userInfo = usersService.findByuserId(usersDto.getUserId());
//		log.info("users: {}", users);
		// 데이터가 없으면 로그인 페이지로 리턴한다.
		if (userInfo == null) {
			rttr.addFlashAttribute("msg", "존재하지 않는 아이디입니다.");
			return "redirect:/";
		};
		// 비밀번호가 일치하지 않으면 로그인 페이지로 리턴한다.
		if (!userInfo.getUserPassword().equals(usersDto.getUserPassword())) {
			rttr.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "redirect:/";
		};
		
		// 아이디가 같은 데이터가 있으면 로그인한다.
		session.setAttribute("userNum", userInfo.getUserNum());
		// 블로그 정보 불러오기
		BlogDto blogDto = blogService.selectBlog(userInfo.getUserNum());
		// 블로그가 없으면 블로그 생성 페이지로 이동
		if (blogDto == null) {
			return "redirect:blog";
		} else {
			session.setAttribute("blogId", blogDto.getBlogId());
			List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogDto.getBlogId());
//			log.info("categoryDto: {}", categoryDto);
			// 카테고리가 없으면 카테고리 생성 페이지로 이동
			if (categoryDto == null || categoryDto.isEmpty()) {
				return "redirect:category";
			} else {
				return "redirect:main";
			}
		}
	};
	
	// 회원가입 페이지로 이동
	@RequestMapping("/join")
	public String join() {
		log.info("LoginController의 join() 메소드 실행");
		return "create/join";
	};
	
	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		log.info("LoginController의 logout() 메소드 실행");
		session.invalidate();
		return "redirect:/";
	};
	
	// 템플릿 설명 페이지로
	@RequestMapping("/TempleteInfo")
	public String TempleteInfo() {
		log.info("LoginController의 TempleteInfo() 메소드 실행");
		return "TempleteInfo";
	};
	
};
