package com.example.TravelProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@Column(name="sns_id", nullable=false, columnDefinition="int")
	private Long snsId; // SNS 고유 번호
//	데이터를 저장할 필드 선언
	@Column(name="sns_url", nullable=false, columnDefinition="varchar(100)")
	private String snsUrl; // SNS 주소
	@Column(name="sns_name", nullable=false, columnDefinition="varchar(20)")
	private String snsName; // SNS 이름
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false, columnDefinition="int") // user_num 컬럼에 Users의 대표값(기본키)을 저장한다.
	private Users users; // 유저 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(SNS, 사용자)
	public static SNS toEntity(SNS sns, Users users) {
		// Entity 저장 및 반환
		return new SNS(sns.getSnsId(), sns.getSnsUrl(), sns.getSnsName(), users);
	};
	
};
