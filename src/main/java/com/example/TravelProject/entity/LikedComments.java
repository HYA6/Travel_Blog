package com.example.TravelProject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("serial")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
@Table(name="LikedComments")
public class LikedComments implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="liked_id", nullable=false, columnDefinition="int")
	private Long likedCommentId; // 댓글 좋아요 고유 번호
	@Column(name="liked", columnDefinition="VARCHAR(10)")
	private String likedComment; // 댓글 좋아요 여부(Y/N/null)
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false, columnDefinition="int")
	private Users users; // 유저 고유 번호
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "comment_id", nullable=false, columnDefinition="int")
	private Comments comments; // 댓글 고유 번호
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static LikedComments toEntity(LikedComments commentsLikes, Users users, Comments comments) {
		log.info("CommentsLikes의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new LikedComments(commentsLikes.getLikedCommentId(), commentsLikes.getLikedComment(), users, comments);
	}
	
}
