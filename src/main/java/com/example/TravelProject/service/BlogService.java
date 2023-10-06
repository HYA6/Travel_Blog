package com.example.TravelProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TravelProject.dto.BlogDto;
import com.example.TravelProject.entity.Blog;
import com.example.TravelProject.entity.Users;
import com.example.TravelProject.repository.BlogRepository;
import com.example.TravelProject.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BlogService {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private BlogRepository blogRepository;
	
	// 블로그 생성
	@Transactional
	public void blogCreate(BlogDto dto, Long userNum) {
		log.info("BlogService의 blogCreate() 메소드 실행");
		// 블로그를 저장하려는 유저가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Users users = usersRepository.findById(userNum)
				.orElseThrow(() -> new IllegalArgumentException("블로그 생성 실패! 대상 유저가 없습니다."));
		// dto를 entity로 변환
		Blog blog = Blog.toEntity(dto, users);
//		log.info("blog: {}", blog);
		// 블로그 저장
		blogRepository.save(blog);
	}
	
	// 로그인한 유저의 블로그 찾기
	@Transactional
	public BlogDto selectBlog(Long userNum) {
		log.info("BlogService의 selectBlog() 메소드 실행");
		Blog blog = blogRepository.findById(userNum).orElse(null);
//		log.info("blog: {}", blog);
		try {
			return BlogDto.toDto(blog);
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	// 블로그 주소 찾기
	public String findBlogUrl(String blogUrl) {
		log.info("BlogService의 findBlogUrl() 메소드 실행");
		String url = blogRepository.findByUrl(blogUrl);
//		log.info("url: {}", url);
		return url;
	}
	
	// 블로그 수정
	public void blogEditOK(BlogDto blogDto) {
		log.info("BlogService의 blogEditOK() 메소드 실행");
		// 수정하려는 블로그가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Blog blog = blogRepository.findById(blogDto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("블로그 수정 실패! 대상 블로그가 없습니다."));
		// 블로그 수정, 블로그 갱신
		blog.update(blogDto);
		// 수정된 블로그로 다시 저장한다.
		blogRepository.save(blog);
	}
	
	// 블로그 삭제
	public void blogDelete(Long blogId) {
		log.info("BlogService의 blogDelete() 메소드 실행");
		blogRepository.deleteById(blogId);
	}

}