package com.example.TravelProject.comment.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.example.TravelProject.auth.entity.Users;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LikedComments implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="liked_id", nullable=false)
	private Long likedCommentId; // 댓글 좋아요 고유 번호
	@Column(name="liked", length = 10)
	private String likedComment; // 댓글 좋아요 여부(Y/N/null)
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false)
	private Users users; // 유저 고유 번호
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "comment_id", nullable=false)
	private Comments comments; // 댓글 고유 번호
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static LikedComments toEntity(LikedComments commentsLikes, Users users, Comments comments) {
		// Entity 생성 및 반환
		return new LikedComments(commentsLikes.getLikedCommentId(), commentsLikes.getLikedComment(), users, comments);
	}
	
}
