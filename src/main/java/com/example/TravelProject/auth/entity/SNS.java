package com.example.TravelProject.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SNS {

//	기본키로 사용할 필드 선언
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sns_id", nullable=false)
	private Long snsId; // SNS 고유 번호
//	데이터를 저장할 필드 선언
	@Column(name="sns_name", nullable=false, length = 20)
	private String snsName; // SNS 이름
	@Column(name="sns_url", nullable=false, length = 100)
	private String snsUrl; // SNS 주소
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false) // user_num 컬럼에 Users의 대표값(기본키)을 저장한다.
	private Users users; // 유저 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(SNS, 사용자)
	public static SNS toEntity(SNS sns, Users users) {
		// Entity 저장 및 반환
		return new SNS(sns.getSnsId(), sns.getSnsUrl(), sns.getSnsName(), users);
	};
	
};
