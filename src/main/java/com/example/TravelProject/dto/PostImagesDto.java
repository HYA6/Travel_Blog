package com.example.TravelProject.dto;

import com.example.TravelProject.entity.PostImages;

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
public class PostImagesDto {
	
	private Long postImageId; // 게시글 이미지 고유 번호
	private String postImageName; // 게시글 이미지 이름
	private int postImageGup; // 게시글 이미지 그룹
	private int postImageSeq; // 게시글 이미지 출력 순서
	private Long postId; // 게시글 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static PostImagesDto toDto(PostImages postImages) {
		return new PostImagesDto(postImages.getPostImageId(), postImages.getPostImageName(), 
				postImages.getPostImageGup(), postImages.getPostImageSeq(), postImages.getPost().getPostId());
	};
	
};