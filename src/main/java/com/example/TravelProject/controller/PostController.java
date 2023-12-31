package com.example.TravelProject.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.example.TravelProject.dto.CommentsDto;
import com.example.TravelProject.dto.LikedPostDto;
import com.example.TravelProject.dto.PostContentsDto;
import com.example.TravelProject.dto.PostTextsDto;
import com.example.TravelProject.dto.PostVisitDto;
import com.example.TravelProject.dto.PostDto;
import com.example.TravelProject.dto.PostImagesDto;
import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.service.BlogService;
import com.example.TravelProject.service.CategoryService;
import com.example.TravelProject.service.CommentsService;
import com.example.TravelProject.service.LikedPostService;
import com.example.TravelProject.service.PostService;
import com.example.TravelProject.service.PostVisitService;
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
	@Autowired
	private CommentsService commentsService;
	@Autowired
	private PostVisitService postVisitService;
	@Autowired
	private LikedPostService likedPostService;
	
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
	public String singlePost(Model model, @RequestParam Long postId, HttpSession session) {
		log.info("PostController의 singlePost() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");
		
		// 로그인한 유저 정보 가져오기
		UsersDto usersDto = usersService.selectIUser(userNum);
		
		// 로그인한 유저 블로그 정보 가져오기
		BlogDto blogDto = blogService.selectBlog(userNum);
//		log.info("blogDto: {}", blogDto);
		
		// 블로그에 있는 카테고리 전부 가져오기
		List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogDto.getBlogId());
		
		// 조회수 가져오기
		Date nowdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(nowdate);
//		log.info("date: {}", date);
		String visitDate = postVisitService.findDate(userNum, postId);
//		log.info("visitDate: {}", visitDate);
		// 조회한 날짜가 다르면 조회수 올리기
		if (!date.equals(visitDate)) {
			PostVisitDto postVisitDto = new PostVisitDto(null, nowdate, userNum, postId);
			postVisitService.visitUp(postVisitDto);
		};
		// 게시글의 전체 조회수 검색
		int visitCount = postVisitService.selectAllVisit(postId);
//		log.info("visitCount: {}", visitCount);

		// 전체 좋아요 수 가져오기
		int likeCount = likedPostService.selectAllLikes(postId);
		// 좋아요 정보 가져오기
		LikedPostDto likedPostDto = likedPostService.findliked(userNum, postId);
		// 좋아요 정보 model로 넘기기
		if (likedPostDto == null) {
			model.addAttribute("likedPostDto", "");
		} else {
			model.addAttribute("likedPostDto", likedPostDto);
		};
		
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
		postDto.setPostVisits(visitCount);
		postDto.setPostLikes(likeCount);
		model.addAttribute("postDto", postDto);
		
		// 태그 가져오기
		String[] postTags = postDto.getPostTag().split(",");
//		for(int i=0; i<postTags.length; i++) {
//			log.info("태그: {}", postTags[i]);
//		};
		model.addAttribute("postTags", postTags);
		
		// 양식에 따른 게시글 내용 가져오기
		if (postDto.getPostForm().equals("standard")) {
			// 기본 양식
			List<PostContentsDto> contents = new ArrayList<PostContentsDto>();
			for (PostTextsDto textDto : textsList) {
				PostContentsDto dto = PostContentsDto.textToContent(textDto, "text");
				contents.add(dto);
			};
			for (PostImagesDto imageDto : imagesList) {
				PostContentsDto dto = PostContentsDto.imageToContent(imageDto, "image");
				contents.add(dto);
			};
			for (int i=0; i < contents.size()-1; i++) {
				if (contents.get(i).getPostContentSeq() > contents.get(i+1).getPostContentSeq()) {
					PostContentsDto dto = contents.get(i);
					contents.set(i, contents.get(i+1));
					contents.set(i+1, dto);
				};
			};
//			log.info("contents: {}", contents);
			model.addAttribute("contents", contents);
		} else {
			// 간단 양식
			model.addAttribute("textsList", textsList);
			model.addAttribute("imagesList", imagesList);
		};
		
		// 전체 댓글 수 가져오기
		int commentCount = commentsService.selectAllComments(postId);
		// 댓글 가져오기
		List<CommentsDto> commentsDto = commentsService.findCommentsByPostId(postId);
		// 댓글이 있으면 model로 넘기기
		model.addAttribute("commentsDto", commentsDto);
		
		// 인기글
		List<PostDto> popularPost = postService.selectPopularPost(blogDto.getBlogId());
		model.addAttribute("popularPost", popularPost); // 인기글 목록
		// 블로그에 있는 최신 댓글 가져오기(5개)
		List<CommentsDto> recentComment = commentsService.selectRecentComment(blogDto.getBlogId());
		model.addAttribute("recentComment", recentComment); // 최신 댓글 목록
		
		// 각 게시글 조회수, 좋아요수, 댓글수 저장
		postDto.setPostComments(commentCount);
		postService.savePost(postDto);
		
		return "singlePost";
	};
	
};
