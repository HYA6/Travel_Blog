package com.example.TravelProject.dto;

import java.util.Date;

import com.example.TravelProject.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class PostDto {
	
	private Long postId; // 게시글 고유 번호
	private String postForm; // 게시글 양식
	private String postStratDate; // 게시글 여행 첫 날짜
	private String postEndDate; // 게시글 여행 마지막 날짜
	private String postPlace; // 게시글 여행 장소
	private String postSubject; // 게시글 제목
	private String postTag; // 게시글 태그
	private String postThumbnail; // 게시글 대표 이미지 이름
	private String postThumbnailPath; // 게시글 대표 이미지 경로
	private Date postWrite; // 게시글 작성일
	private Date postUpdate; // 게시글 수정일
	private Long blogId; // 블로그 고유 번호
	private Long categoryId; // 카테고리 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static PostDto toDto(Post post) {
		log.info("PostDto의 toDto() 메소드 실행");
		return new PostDto(post.getPostId(), post.getPostForm(), post.getPostStratDate(), post.getPostEndDate(), 
				post.getPostPlace(), post.getPostSubject(), post.getPostTag(), post.getPostThumbnail(), post.getPostThumbnailPath(),
				post.getPostWrite(), post.getPostUpdate(), post.getBlog().getBlogId(), post.getCategory().getCategoryId());
	}
	
}