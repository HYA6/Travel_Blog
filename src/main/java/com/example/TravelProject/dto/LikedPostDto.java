package com.example.TravelProject.dto;

import com.example.TravelProject.entity.LikedPost;

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
public class LikedPostDto {
	
	private Long likedPostId; // 게시글 좋아요 고유 번호
	private String likedPost; // 게시글 좋아요 여부(Y/null)
	private Long userNum; // 유저 고유 번호
	private Long postId; // 게시글 고유 번호
	
	// 엔티티를 dto로
	public static LikedPostDto toDto(LikedPost likedPost) {
		return new LikedPostDto(likedPost.getLikedPostId(), likedPost.getLikedPost(),
				likedPost.getUsers().getUserNum(), likedPost.getPost().getPostId());
	};
	
};
