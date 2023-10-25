package com.example.TravelProject.dto;

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
// 게시글 내용 기본 양식을 위한 Dto
public class PostContentsDto {
	
	private Long postContentId; // 게시글 내용 고유 번호
	private String postContentType; // 게시글 내용 타입(글인지 이미지인지)
	private String postContent; // 게시글 내용
	private int postContentGup; // 게시글 내용 그룹
	private int postContentSeq; // 게시글 내용 순서
	private Long postId; // 게시글 고유 번호
	
	// textsDto를 contentsDto로 변환
	public static PostContentsDto textToContent(PostTextsDto dto, String type) {
		return new PostContentsDto(dto.getPostTextId(), type, dto.getPostText(), dto.getPostTextGup(),
				dto.getPostTextSeq(), dto.getPostId());
	};
	
	// imagesDto를 contentsDto로 변환
	public static PostContentsDto imageToContent(PostImagesDto dto, String type) {
		return new PostContentsDto(dto.getPostImageId(), type, dto.getPostImageName(), 
				dto.getPostImageGup(), dto.getPostImageSeq(), dto.getPostId());
	};
	
};