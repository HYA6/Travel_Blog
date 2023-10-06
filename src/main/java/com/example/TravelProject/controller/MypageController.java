package com.example.TravelProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.entity.Users;
import com.example.TravelProject.repository.UsersRepository;
import com.example.TravelProject.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MypageController {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UsersService usersService;
	
	// 마이 페이지로 이동
	@RequestMapping("/mypage")
	public String mypage(Model model, HttpSession session) {
		log.info("MypageController의 mypage() 메소드");
		
		Long userNum = (Long) session.getAttribute("userNum");
//		log.info("userNum: {}", userNum);
		
		// 로그인한 사용자 정보 가져오기
		Users users = usersRepository.findById(userNum).orElse(null);
//		log.info("users: {}", users);
		
		UsersDto usersDto = UsersDto.toDto(users);
		
		model.addAttribute("usersDto", usersDto);
		return "mypage";
	};
	
	// 내 정보 수정 페이지로 이동
	@RequestMapping("/myInfoEdit")
	public String myInfoEdit(Model model, HttpSession session) {
		log.info("MypageController의 myInfoEdit() 메소드");
		
		Long userNum = (Long) session.getAttribute("userNum");
//		log.info("userNum: {}", userNum);
		
		// 로그인한 사용자 정보 가져오기
		Users users = usersRepository.findById(userNum).orElse(null);
//		log.info("users: {}", users);
		
		UsersDto usersDto = UsersDto.toDto(users);
//		log.info("usersDto.getUsers_gender(): {}", usersDto.getUsers_gender());
		
		model.addAttribute("usersDto", usersDto);
		return "edit/myInfoEdit";
	};
	
	// 내 정보 수정하기
	@RequestMapping("/myInfoEditOK")
	public String myInfoEditOK(Model model, UsersDto usersDto) {
		log.info("MypageController의 myInfoEditOK() 메소드");
		
		// 로그인한 사용자 정보 수정하기
		UsersDto dto = usersService.updateInfo(usersDto);
		
		model.addAttribute("usersDto", dto);
		return "edit/myInfoEdit";
	};
	
	// 내 정보 삭제(회원 탈퇴) 후 로그인 페이지로 돌아가기
	@RequestMapping("/myInfoDelete")
	public String myInfoDelete() {
		log.info("MypageController의 myInfoDelete() 메소드");
		return "redirect:/";
	};

}
