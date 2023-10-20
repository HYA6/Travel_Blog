package com.example.TravelProject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TravelProject.dto.BlogDto;
import com.example.TravelProject.dto.CategoryDto;
import com.example.TravelProject.dto.PostTextsDto;
import com.example.TravelProject.dto.PostDto;
import com.example.TravelProject.dto.PostImagesDto;
import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.service.BlogService;
import com.example.TravelProject.service.CategoryService;
import com.example.TravelProject.service.PostService;
import com.example.TravelProject.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PostController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
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
//		log.info("categoryDto: {}", categoryDto);
		
		model.addAttribute("userNum", userNum);
		model.addAttribute("blogId", blogId);
		model.addAttribute("categoryDto", categoryDto);
		
		return "create/postCreate";
	};
	
	// 게시글 저장(파일 디렉토리에 업로드) 후 메인 페이지
	@RequestMapping("/postToMain")
	public String postToMain() {
		log.info("PostController의 postToMain() 메소드");
		return "redirect:main";
	};
	
	// 게시글 1건 보기
	@GetMapping("/singlePost")
	public String singlePost(Model model,@RequestParam Long postId, HttpSession session) {
		log.info("PostController의 singlePost() 메소드");
		
		Long userNum = (Long) session.getAttribute("userNum");
		
		// 로그인한 유저 정보 가져오기
		UsersDto usersDto = usersService.selectIUser(userNum);
		
		// 로그인한 유저 블로그 정보 가져오기
		BlogDto blogDto = blogService.selectBlog(userNum);
//		log.info("blogDto: {}", blogDto);
		
		// 블로그에 있는 카테고리 전부 가져오기
		List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogDto.getBlogId());
		
		// 게시글 1건 가져오기
//		log.info("postId: {}", postId);
		PostDto postDto = postService.findPostById(postId);
//		log.info("postDto: {}", postDto);
		List<PostTextsDto> textsList = postService.findTextsByPostId(postId);
//		log.info("contentsDtoList: {}", contentsDtoList);
		List<PostImagesDto> imagesList = postService.findImagesByPostId(postId);
//		log.info("imagesDtoList: {}", imagesDtoList);
		
		model.addAttribute("usersDto", usersDto);
		model.addAttribute("blogDto", blogDto);
		model.addAttribute("categoryDto", categoryDto);
		model.addAttribute("postDto", postDto);
		
		String[] postTags = postDto.getPostTag().split(",");
//		for(int i=0; i<postTags.length; i++) {
//			log.info("태그: {}", postTags[i]);
//		};
		model.addAttribute("postTags", postTags);
		
		String[] postContents = new String[100];
		if (postDto.getPostForm().equals("standard")) {
			// 기본 양식
			for(int i=0; i < textsList.size(); i++) {
				for(int j=0; j < imagesList.size(); j++) {
					int textsGup = textsList.get(i).getPostTextGup();
					int imagesGup = imagesList.get(j).getPostImageGup();
					if (imagesGup < textsGup) {
						postContents[i] = imagesList.get(i).getPostImageName();
					} else {
						postContents[i] = textsList.get(i).getPostText();
						break;
					}
				};
				log.info("postContents: {}", postContents[i]);
			};
		} else {
			// 간단 양식
			model.addAttribute("textsList", textsList);
			model.addAttribute("imagesList", imagesList);
		};
		
		return "single";
	};
	
};
