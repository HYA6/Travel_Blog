package com.example.TravelProject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelProject.service.BlogService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BlogApiController {
	
	@Autowired
	private BlogService blogService;
	
	// 블로그 주소 중복 조회 fetch
	@GetMapping("/api/blogUrlCheck/{blogUrl}")
	public ResponseEntity<String> find(@PathVariable String blogUrl) {
		log.info("BlogApiController의 find() 메소드 실행");
		String url = "";
		try {
			url = blogService.findBlogUrl(blogUrl);
		} catch (Exception e) {
			url = null;
		}
		log.info(url);
		return url == null ?
				ResponseEntity.status(HttpStatus.OK).body(url) :
				ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	// 블로그 삭제
	@RequestMapping("/api/blogDelete")
	public ResponseEntity<String> blogDelete(@RequestParam("blogId") Long blogId) {
		log.info("BlogApiController의 blogDelete() 메소드 실행");
		// 블로그 삭제하기
		blogService.blogDelete(blogId);
		return ResponseEntity.status(HttpStatus.OK).body(blogId + "");
	}
	
}
