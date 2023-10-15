package com.example.TravelProject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@IdClass(Users.class)
@Table(name="UserProfile")
public class UserProfile implements Serializable {

	@Column(name="user_image", columnDefinition="varchar(100)")
	private String userImage; // 프로필 이미지
	@Column(name="user_info", columnDefinition="varchar(300)")
	private String userInfo; // 프로필 소개글
	// 외래키
	@Id
	@OneToOne // 1:1 단방향 관계
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false, columnDefinition="int") // user_num 컬럼에 Users의 대표값(기본키)을 저장한다.
	private Users users; // 유저 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(SNS, 사용자)
	public static UserProfile toEntity(UserProfile userInfo, Users users) {
		log.info("UserInfo의 toEntity() 메소드 실행");
		// Entity 저장 및 반환
		return new UserProfile(userInfo.getUserImage(), userInfo.getUserInfo(), users);
	};
	
};
