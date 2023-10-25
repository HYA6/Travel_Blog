package com.example.TravelProject.dto;

import com.example.TravelProject.entity.Blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogDto {
	
	private Long blogId; // 블로그 고유 번호
	private String blogName; // 블로그 이름
	private String blogThema; // 블로그 테마
	private String blogUrl; // 블로그 주소
	private Long userNum; // 유저 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static BlogDto toDto(Blog blog) {
		return new BlogDto(blog.getBlogId(), blog.getBlogName(), blog.getBlogThema(), 
				blog.getBlogUrl(), blog.getUsers().getUserNum());
	};
	
};