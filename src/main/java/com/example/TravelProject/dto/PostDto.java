package com.example.TravelProject.dto;

import java.util.Date;

import com.example.TravelProject.entity.Post;

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
public class PostDto {
	
	private Long postId; // 게시글 고유 번호
	private String postForm; // 게시글 양식
	private String postStartDate; // 게시글 여행 첫 날짜
	private String postEndDate; // 게시글 여행 마지막 날짜
	private String postPlace; // 게시글 여행 장소
	private String postSubject; // 게시글 제목
	private String postTag; // 게시글 태그
	private String postThumbnail; // 게시글 대표 이미지 이름
	private Date postWrite; // 게시글 작성일
	private Date postUpdate; // 게시글 수정일
	private int postVisits; // 게시글 조회수
	private int postLikes; // 게시글 좋아요 수
	private int postComments; // 게시글 전체 댓글 수
	private Long blogId; // 블로그 고유 번호
	private Long categoryId; // 카테고리 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static PostDto toDto(Post post) {
		return new PostDto(post.getPostId(), post.getPostForm(), post.getPostStartDate(), post.getPostEndDate(), 
				post.getPostPlace(), post.getPostSubject(), post.getPostTag(), post.getPostThumbnail(), post.getPostWrite(), 
				post.getPostUpdate(), post.getPostVisits(), post.getPostLikes(), post.getPostComments(),
				post.getBlog().getBlogId(), post.getCategory().getCategoryId());
	};
	
};