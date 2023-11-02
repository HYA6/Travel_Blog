package com.example.TravelProject.dto;

import java.util.Date;

import com.example.TravelProject.entity.Comments;

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
public class CommentsDto {
	
	private Long commentId; // 댓글 고유 번호
	private String commentContent; // 댓글 내용
	private Date commentDate; // 댓글 작성일
	private int commentGup; // 댓글 그룹
	private int commentLev; // 댓글 레벨
	private int commentSeq; // 댓글 출력 순서
	private String commentDel; // 댓글 삭제 여부
	private Long userNum; // 유저 고유 번호
	private String userName; // 유저 이름
	private String userNickname; // 유저 닉네임
	private Long blogId; // 블로그 고유 번호
	private Long postId; // 게시글 고유 번호
	
	// Entity를 Dto로 변환
	public static CommentsDto toDto(Comments comments) {
		return new CommentsDto(comments.getCommentId(), comments.getCommentContent(), comments.getCommentDate(),
				comments.getCommentGup(), comments.getCommentLev(), comments.getCommentSeq(), comments.getCommentDel(),
				comments.getUsers().getUserNum(), comments.getUsers().getUserName(),
				comments.getUsers().getUserNickname(), comments.getBlog().getBlogId(), comments.getPost().getPostId());
	};
	
};