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
	@Column(name="comments_id", nullable=false, columnDefinition="int")
	private Long commentsId; // 댓글 고유 번호
	@Column(name="comments_content", nullable=false, columnDefinition="varchar(200)")
	private String commentsContent; // 댓글 내용
	@Column(name="comments_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date commentsDate; // 댓글 작성일
	@Column(name="comments_gup", columnDefinition="int")
	private String commentsGup; // 댓글 그룹
	@Column(name="comments_seq", columnDefinition="int")
	private String commentsSeq; // 댓글 순서
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "users_num", nullable=false, columnDefinition="int")
	private Users users; // 유저 고유 번호
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "post_id", nullable=false, columnDefinition="int")
	private Post post; // 게시글 고유 번호
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static Comments toEntity(Comments comments, Users users, Post post) {
		log.info("Comments의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new Comments(comments.getCommentsId(), comments.getCommentsContent(), comments.getCommentsDate(),
				comments.getCommentsGup(), comments.getCommentsSeq(), users, post);
	}
	
}
