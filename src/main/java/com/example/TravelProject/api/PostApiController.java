package com.example.TravelProject.api;

import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelProject.dto.PostContentsDto;
import com.example.TravelProject.dto.PostDto;
import com.example.TravelProject.dto.PostImagesDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PostApiController {
	
	// 게시글 저장
	@ResponseBody
	@RequestMapping("/api/uploadPost")
	public String uploadPost(PostDto postDto, Model model) {
		log.info("PostApiController의 uploadPost() 메소드 실행"); 
		// 오늘 날짜를 작성일에 저장
		Date nowDate = new Date();
		postDto.setPostWrite(nowDate);
		
		log.info("postDto: {}", postDto);
		
		return "테스트 성공";
	};
	
	// 이미지 저장
	@ResponseBody
	@RequestMapping("/api/uploadImage")
	public String uploadImage(PostImagesDto postImagesDto, Model model) {
		log.info("PostApiController의 uploadImage() 메소드 실행"); 
		
		log.info("postImagesDto: {}", postImagesDto);
		
		return "테스트 성공";
	};
	
	// 내용 저장
	@ResponseBody
	@RequestMapping("/api/uploadContent")
	public String uploadContent(PostContentsDto postContentsDto, Model model) {
		log.info("PostApiController의 uploadContent() 메소드 실행"); 
		
		log.info("postContentsDto: {}", postContentsDto);
		
		return "테스트 성공";
	};
	
};
