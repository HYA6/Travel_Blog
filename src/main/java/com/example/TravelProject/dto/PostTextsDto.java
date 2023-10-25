package com.example.TravelProject.dto;

import com.example.TravelProject.entity.PostTexts;

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
public class PostTextsDto {
	
	private Long postTextId; // 게시글 내용 고유 번호
	private String postText; // 게시글 내용
	private int postTextGup; // 게시글 내용 그룹
	private int postTextSeq; // 게시글 내용 출력 순서
	private Long postId; // 게시글 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static PostTextsDto toDto(PostTexts postTexts) {
		return new PostTextsDto(postTexts.getPostTextId(), postTexts.getPostText(), 
				postTexts.getPostTextGup(), postTexts.getPostTextSeq(), postTexts.getPost().getPostId());
	};
	
};