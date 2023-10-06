package com.example.TravelProject.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TravelProject.dto.BlogDto;
import com.example.TravelProject.dto.CategoryDto;
import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.service.BlogService;
import com.example.TravelProject.service.CategoryService;
import com.example.TravelProject.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JoinController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	// 아이디가 있는지 확인 후 유저 회원가입 및 로그인
	@RequestMapping("/usersInsert")
	public String usersInsert(HttpSession session, Model model, UsersDto usersDto, RedirectAttributes rttr) {
		log.info("JoinController의 usersInsert() 메소드");
		Date nowDate = new Date();
		
		// DTO 데이터 처리
		if (usersDto.getUserId() == null) {
			// form 사용X (로그인 API 이용)
			usersDto = (UsersDto) session.getAttribute("usersDto");
		} else {
			// form으로 넘어온 생일 데이터로 나이 저장 (회원가입 이용)
			int birthYear = Integer.parseInt(usersDto.getUserBirhtday().substring(0,4));
//			log.info("birthYear: {}", birthYear);
			@SuppressWarnings("deprecation")
			int year = nowDate.getYear() + 1900;
			usersDto.setUserAge(year - birthYear);
		}
		// 생성일에 오늘 날짜 넣어주기(yyyy-MM-dd)
		usersDto.setUserCreateDate(nowDate);
		
		// 이미 있는 유저 고유 번호인지 확인
		UsersDto userInfo = usersService.findByuserId(usersDto.getUserId());
//		log.info("userInfo: {}", userInfo);
		Long userEntityId = null;
		try {
			userEntityId = userInfo.getUserNum();
		} catch (NullPointerException e) { }
//		log.info("userEntityId: {}", userEntityId);
		
		// 저장된 데이터나 유저 고유 번호가 없으면 DB에 새로 저장한다.
		if (userInfo == null || userEntityId == null) {
			// 로그인 API로 로그인했을 때 이미 가입된 이메일인지 확인 (회원가입은 회원가입 페이지에서 확인함)
			UsersDto usersEmailChk = usersService.findByuserEmail(usersDto.getUserEmail());
			if (usersEmailChk != null) { 
				rttr.addFlashAttribute("msg", "이미 가입된 이메일입니다.");
				return "redirect:/";
			}
			// 유저 정보 저장
			usersService.saveUser(usersDto);
			// 저장된 유저 정보 가져오기
			userInfo = usersService.findByuserId(usersDto.getUserId());
			// 유저 고유 번호
			userEntityId = userInfo.getUserNum();
//			log.info("userEntityId: {}", userEntityId);
			session.setAttribute("userNum", userEntityId);
			// 새로 가입한 유저면 블로그 생성 페이지로 이동
			return "redirect:blog";
		} else {
			session.setAttribute("userNum", userEntityId);
			// 이미 가입한 유저지만 블로그가 없으면 블로그 생성 페이지로 이동
			BlogDto blogDto = blogService.selectBlog(userEntityId);
			if (blogDto == null) {
				return "redirect:blog";
			} else {
				session.setAttribute("blogId", blogDto.getBlogId());
				List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogDto.getBlogId());
//				log.info("categoryDto: {}", categoryDto);
				// 이미 가입한 유저고 블로그는 있지만 카테고리가 없으면 카테고리 생성 페이지로 이동
				if (categoryDto == null || categoryDto.isEmpty()) {
					return "redirect:category";
				} else {
					return "redirect:main";
				}
			}
		}
	}
}
