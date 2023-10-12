package com.example.TravelProject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comment_id", nullable=false, columnDefinition="int")
	private Long commentId; // 댓글 고유 번호
	@Column(name="comment_content", nullable=false, columnDefinition="varchar(200)")
	private String commentContent; // 댓글 내용
	@Column(name="comment_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date commentDate; // 댓글 작성일
	@Column(name="comment_gup", columnDefinition="int")
	private int commentGup; // 댓글 그룹
	@Column(name="comment_lev", columnDefinition="int")
	private int commentLev; // 댓글 레벨
	@Column(name="comment_seq", columnDefinition="int")
	private int commentSeq; // 댓글 출력 순서
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false, columnDefinition="int")
	private Users users; // 유저 고유 번호
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "post_id", nullable=false, columnDefinition="int")
	private Post post; // 게시글 고유 번호
	
//	DTO 데이터를 Entity로 변환하는 메소드
	public static Comments toEntity(Comments comments, Users users, Post post) {
		log.info("Comments의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new Comments(comments.getCommentId(), comments.getCommentContent(), comments.getCommentDate(),
				comments.getCommentGup(), comments.getCommentLev(), comments.getCommentSeq(), users, post);
	}
	
}
