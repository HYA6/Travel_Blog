package com.example.TravelProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TravelProject.dto.PostContentsDto;
import com.example.TravelProject.dto.PostDto;
import com.example.TravelProject.dto.PostImagesDto;
import com.example.TravelProject.entity.Blog;
import com.example.TravelProject.entity.Category;
import com.example.TravelProject.entity.Post;
import com.example.TravelProject.entity.PostContents;
import com.example.TravelProject.entity.PostImages;
import com.example.TravelProject.repository.BlogRepository;
import com.example.TravelProject.repository.CategoryRepository;
import com.example.TravelProject.repository.PostContentsRepository;
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
	private PostContentsRepository postContentsRepository;
	
	// 게시글 저장
	@Transactional
	public void createPost(PostDto postDto) {
		log.info("PostService의 createPost() 메소드 실행");
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
	}
	
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
	public void saveContent(PostContentsDto postContentsDto) {
		log.info("PostService의 saveContent() 실행");
		Post post = postRepository.findById(postContentsDto.getPostId())
				.orElseThrow(() -> new IllegalArgumentException("내용 저장 실패! 대상 게시글이 없습니다."));
		// dto를 entity로 변환
		PostContents postContents = PostContents.toEntity(postContentsDto, post);
		// 내용 저장
		postContentsRepository.save(postContents);
	};
	
};