package com.example.TravelProject.dto;

import java.util.Date;

import com.example.TravelProject.entity.PostVisit;

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
public class PostVisitDto {
	
	private Long postVisitId; // 게시글 조회수 고유 번호
	private Date postVisitDate; // 게시글 조회한 날짜
	private Long userNum; // 유저 고유 번호
	private Long postId; // 게시글 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static PostVisitDto toDto(PostVisit postVisit) {
		return new PostVisitDto(postVisit.getPostVisitId(), postVisit.getPostVisitDate(),
				postVisit.getUsers().getUserNum(), postVisit.getPost().getPostId());
	};
	
}
