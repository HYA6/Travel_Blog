package com.example.TravelProject.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TravelProject.dto.CommentsDto;
import com.example.TravelProject.service.CommentsService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommentController {
	
	@Autowired
	private CommentsService commentsService;
	
	// 댓글 저장
	@RequestMapping("/saveComment")
	public String saveComment(CommentsDto commentsDto, RedirectAttributes re) {
		log.info("CommentController의 saveComment()");
//		log.info("commentsDto: {}", commentsDto);
		// 오늘 날짜를 작성일에 저장하기
		Date date = new Date();
		commentsDto.setCommentDate(date);
		// 저장하기
		commentsService.saveComment(commentsDto);
		// 값 넘겨주기
		re.addAttribute("postId", commentsDto.getPostId());
		return "redirect:singlePost";
	}
	
	// 댓글 삭제
	@RequestMapping("/deleteComment")
	public String deleteComment(CommentsDto commentsDto, RedirectAttributes re) {
		log.info("CommentController의 saveComment()");
//		log.info("commentsDto: {}", commentsDto);
		commentsDto.setCommentDel("Y");
		// 저장하기
		commentsService.deleteComment(commentsDto);
		// 값 넘겨주기
		re.addAttribute("postId", commentsDto.getPostId());
		return "redirect:singlePost";
	}
	
}
