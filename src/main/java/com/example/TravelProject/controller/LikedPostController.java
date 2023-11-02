package com.example.TravelProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TravelProject.dto.LikedPostDto;
import com.example.TravelProject.service.LikedPostService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LikedPostController {
	
	@Autowired
	private LikedPostService likedPostService;
	
	// 게시글 좋아요 저장
	@GetMapping("/savePostLike")
	public String savePostLike(@RequestParam Long postId, HttpSession session, RedirectAttributes re) {
		log.info("LikedPostController의 savePostLike() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");
		// Dto에 좋아요 저장
		LikedPostDto likedPostDto = new LikedPostDto(null, "Y", userNum, postId);
		// 저장
		likedPostService.savePostLiked(likedPostDto);
		// 게시글 번호 넘겨주기
		re.addAttribute("postId", postId);
		return "redirect:singlePost";
	};
	
	// 게시글 좋아요 삭제
	@GetMapping("/deletePostLike")
	public String deletePostLike(@RequestParam Long likedPostId, @RequestParam Long postId, RedirectAttributes re) {
		log.info("LikedPostController의 deletePostLike() 메소드");
		// 삭제
		likedPostService.deletePostLike(likedPostId);
		// 게시글 번호 넘겨주기
		re.addAttribute("postId", postId);
		return "redirect:singlePost";
	};
	
};
