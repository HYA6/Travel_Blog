package com.example.TravelProject.dto;

import com.example.TravelProject.entity.PostContents;

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
public class PostContentsDto {
	
	private Long postContentId; // 게시글 내용 고유 번호
	private String postContent; // 게시글 내용
	private int postContentGup; // 게시글 내용 그룹
	private int postContentSeq; // 게시글 내용 출력 순서
	private Long postId; // 게시글 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static PostContentsDto toDto(PostContents postContents) {
		log.info("PostContentsDto의 toDto() 메소드 실행");
		return new PostContentsDto(postContents.getPostContentId(), postContents.getPostContent(), 
				postContents.getPostContentGup(), postContents.getPostContentSeq(), 
				postContents.getPost().getPostId());
	};
	
};