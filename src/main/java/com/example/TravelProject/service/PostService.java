package com.example.TravelProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TravelProject.dto.PostTextsDto;
import com.example.TravelProject.dto.PostDto;
import com.example.TravelProject.dto.PostImagesDto;
import com.example.TravelProject.entity.Blog;
import com.example.TravelProject.entity.Category;
import com.example.TravelProject.entity.Post;
import com.example.TravelProject.entity.PostTexts;
import com.example.TravelProject.entity.PostImages;
import com.example.TravelProject.repository.BlogRepository;
import com.example.TravelProject.repository.CategoryRepository;
import com.example.TravelProject.repository.PostTextsRepository;
import com.example.TravelProject.repository.PostImagesRepository;
import com.example.TravelProject.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {
	
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostImagesRepository postImagesRepository;
	@Autowired
	private PostTextsRepository postTextsRepository;
	
	// 게시글 저장 및 수정
	@Transactional
	public void savePost(PostDto postDto) {
		log.info("PostService의 savePost() 메소드 실행");
		Blog blog = blogRepository.findById(postDto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("게시글 저장 실패! 대상 블로그가 없습니다."));
		Category category = categoryRepository.findById(postDto.getCategoryId())
				.orElseThrow(() -> new IllegalArgumentException("게시글 저장 실패! 대상 카테고리가 없습니다."));
		// dto를 entity로 변환
		Post post = Post.toEntity(postDto, blog, category);
		// 게시글 저장
		postRepository.save(post);
	};
	
	// 게시글 한 건 얻어오기
	@Transactional
	public PostDto selectPostByOption(PostDto postDto) {
		log.info("PostService의 selectPost() 메소드 실행");
		Blog blog = blogRepository.findById(postDto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("게시글 찾기 실패! 대상 블로그가 없습니다."));
		Category category = categoryRepository.findById(postDto.getCategoryId())
				.orElseThrow(() -> new IllegalArgumentException("게시글 찾기 실패! 대상 카테고리가 없습니다."));
		// dto를 entity로 변환
		Post post = Post.toEntity(postDto, blog, category);
		// 게시글 찾기
		Post search = postRepository.selectPostByOption(post.getPostSubject(), post.getPostStartDate(), 
				post.getPostEndDate(), post.getBlog().getBlogId(), post.getCategory().getCategoryId());
		log.info("search: {}", search);
		return PostDto.toDto(search);
	};
	
	// 이미지 저장
	@Transactional
	public void saveImage(PostImagesDto postImagesDto) {
		log.info("PostService의 saveImage() 실행");
		Post post = postRepository.findById(postImagesDto.getPostId())
				.orElseThrow(() -> new IllegalArgumentException("이미지 저장 실패! 대상 게시글이 없습니다."));
		// dto를 entity로 변환
		PostImages postImages = PostImages.toEntity(postImagesDto, post);
		// 이미지 저장
		postImagesRepository.save(postImages);
	};
	
	// 내용 저장
	@Transactional
	public void saveText(PostTextsDto postTextsDto) {
		log.info("PostService의 saveText() 실행");
		Post post = postRepository.findById(postTextsDto.getPostId())
				.orElseThrow(() -> new IllegalArgumentException("내용 저장 실패! 대상 게시글이 없습니다."));
		// dto를 entity로 변환
		PostTexts postContents = PostTexts.toEntity(postTextsDto, post);
		// 내용 저장
		postTextsRepository.save(postContents);
	};
	
	// 블로그 고유 번호로 게시글 목록 찾기
	public List<PostDto> selectAllPost(Long blogId) {
		log.info("PostService의 selectAllPost() 실행");
		return postRepository.selectByBlog(blogId)
				.stream()
				.map(post -> PostDto.toDto(post)) // entity를 dto로 변환
				.collect(Collectors.toList());
	};
	
	// 게시글 고유 번호로 게시글 1건 찾기
	public PostDto findPostById(Long postId) {
		log.info("PostService의 findById() 실행");
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("게시글 찾기 실패! 대상 게시글이 없습니다."));
		return PostDto.toDto(post);
	};

	// 게시글 고유 번호로 게시글 내용 목록 찾기
	public List<PostTextsDto> findTextsByPostId(Long postId) {
		log.info("PostService의 findTextsByPostId() 실행");
		return postTextsRepository.findByPostId(postId)
				.stream()
				.map(texts -> PostTextsDto.toDto(texts)) // entity를 dto로 변환
				.collect(Collectors.toList());
	};

	// 게시글 고유 번호로 게시글 이미지 목록 찾기
	public List<PostImagesDto> findImagesByPostId(Long postId) {
		log.info("PostService의 findImagesByPostId() 실행");
		return postImagesRepository.findByPostId(postId)
				.stream()
				.map(images -> PostImagesDto.toDto(images)) // entity를 dto로 변환
				.collect(Collectors.toList());
	};
	
	// 좋아요 많은 순으로 게시글 목록 찾기
	public List<PostDto> selectPopularPost(Long blogId) {
		log.info("PostService의 selectPopularPost() 실행");
		return postRepository.findPopularPost(blogId)
				.stream()
				.map(popularPost -> PostDto.toDto(popularPost)) // entity를 dto로 변환
				.collect(Collectors.toList());
	};
	
};