package com.example.TravelProject.auth.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

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
public class UserProfile implements Serializable {

	@Column(name="user_image", length = 100)
	private String userImage; // 프로필 이미지
	@Column(name="user_info", length = 300)
	private String userInfo; // 프로필 소개글
	// 외래키
	@Id
	@OneToOne // 1:1 단방향 관계
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false) // user_num 컬럼에 Users의 대표값(기본키)을 저장한다.
	private Users users; // 유저 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(SNS, 사용자)
	public static UserProfile toEntity(UserProfile userInfo, Users users) {
		// Entity 저장 및 반환
		return new UserProfile(userInfo.getUserImage(), userInfo.getUserInfo(), users);
	};
	
};
