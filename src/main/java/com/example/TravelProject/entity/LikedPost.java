package com.example.TravelProject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.TravelProject.dto.LikedPostDto;

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
public class LikedPost implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="liked_id", nullable=false, columnDefinition="int")
	private Long likedPostId; // 게시글 좋아요 고유 번호
	@Column(name="liked", columnDefinition="VARCHAR(10)")
	private String likedPost; // 게시글 좋아요 여부(Y/null)
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false, columnDefinition="int")
	private Users users; // 유저 고유 번호
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "post_id", nullable=false, columnDefinition="int")
	private Post post; // 게시글 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static LikedPost toEntity(LikedPostDto likedPostDto, Users users, Post post) {
		// Entity 생성 및 반환
		return new LikedPost(likedPostDto.getLikedPostId(), likedPostDto.getLikedPost(), users, post);
	}
	
}
