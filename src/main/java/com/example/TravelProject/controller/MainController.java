package com.example.TravelProject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TravelProject.dto.BlogDto;
import com.example.TravelProject.dto.CategoryDto;
import com.example.TravelProject.dto.CommentsDto;
import com.example.TravelProject.dto.PostDto;
import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.service.BlogService;
import com.example.TravelProject.service.CategoryService;
import com.example.TravelProject.service.CommentsService;
import com.example.TravelProject.service.PostService;
import com.example.TravelProject.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping("/main")
	public String main(Model model, HttpSession session, @RequestParam(required=false) String currentPage) {
		log.info("MainController의 main() 메소드");
		
		Long userNum = (Long) session.getAttribute("userNum");
		Long blogId = (Long) session.getAttribute("blogId");
		
		// 로그인한 유저 정보 가져오기
		UsersDto usersDto = usersService.selectIUser(userNum);
		
		// 로그인한 유저 블로그 정보 가져오기
		BlogDto blogDto = blogService.selectBlog(userNum);
//		log.info("blogDto: {}", blogDto);
		
		// 블로그에 있는 카테고리 전부 가져오기
		List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogId);
//		log.info("categoryDto: {}", categoryDto);
		
		// 블로그에 있는 전체 게시글 페이지별 목록 가져오기
		int currentPg = 0;
		// 넘어오는 현재 페이지가 있으면 저장한다.
		if (currentPage != null) {
			currentPg = Integer.parseInt(currentPage);
		};
//		log.info("currentPg: {}", currentPg);
		Pageable pageable = PageRequest.of(currentPg, 5);
		List<PostDto> postDto = postService.selectAllPost(blogId, pageable);
//		log.info("postDto: {}", postDto);
		// 전체 페이지 = 게시글 전체 개수 / 5
		int totalPage = postService.selectTotalPost(blogId) / 5;
//		log.info("totalPage: {}", totalPage);
		
		// 블로그에 있는 인기순 게시글 가져오기(5개)
		List<PostDto> popularPost = postService.selectPopularPost(blogId);
//		log.info("popularPost: {}", popularPost);
		
		// 블로그에 있는 최신 댓글 가져오기(5개)
		List<CommentsDto> recentComment = commentsService.selectRecentComment(blogId);
//		log.info("recentComment: {}", recentComment);
		
		model.addAttribute("usersDto", usersDto); // 로그인한 유저 정보
		model.addAttribute("blogDto", blogDto); // 로그인한 유저 블로그 정보
		model.addAttribute("categoryDto", categoryDto); // 카테고리 목록
		model.addAttribute("postDto", postDto); // 게시글 목록
		model.addAttribute("currentPage", currentPg); // 현재 페이지
		model.addAttribute("totalPage", totalPage); // 전체 페이지
		model.addAttribute("popularPost", popularPost); // 인기글 목록
		model.addAttribute("recentComment", recentComment); // 최신 댓글 목록
		
		return "main";
	}
	
}
