package com.example.TravelProject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.TravelProject.dto.UsersDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity // 현재 클래스는 Entity로 사용되는 클래스임을 의미한다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Users {

//	기본키로 사용할 필드 선언
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_num", nullable=false, columnDefinition="int")
	private Long userNum; // 유저 고유 번호
//	데이터를 저장할 필드 선언
	@Column(name="user_id", nullable=false, columnDefinition="varchar(100)") // 필드를 테이블 컬럼과 매핑한다.
	private String userId; // 유저 아이디
	@Column(name="user_name", nullable=false, columnDefinition="varchar(10)")
	private String userName; // 유저 이름
	@Column(name="user_age", nullable=false, columnDefinition="int")
	private int userAge; // 유저 나이
	@Column(name="user_email", nullable=false, columnDefinition="varchar(100)")
	private String userEmail; // 유저 이메일
	@Column(name="user_birhtday", nullable=false, columnDefinition="varchar(20)")
	private String userBirhtday; // 유저 생년월일
	@Column(name="user_password", columnDefinition="varchar(100)")
	private String userPassword; // 유저 비번
	@Column(name="user_phone", columnDefinition="varchar(20)")
	private String userPhone; // 유저 전화번호
	@Column(name="user_gender", columnDefinition="varchar(10)")
	private String userGender; // 유저 성별
	@Column(name="user_nickname", columnDefinition="varchar(20)")
	private String userNickname; // 유저 닉네임
	@Column(name="user_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date userCreateDate; // 유저 생성일
	
//	DTO 데이터를 Entity(테이블과 매핑되는 클래스, Users)로 변환하는(객체를 만드는) 메소드
	public static Users toEntity(UsersDto dto) {
		return new Users(dto.getUserNum(), dto.getUserId(), dto.getUserName(), dto.getUserAge(), dto.getUserEmail(), 
				dto.getUserBirhtday(), dto.getUserPassword(), dto.getUserPhone(), dto.getUserGender(), 
				dto.getUserNickname(), dto.getUserCreateDate());
	};
	
	// 유저 정보를 수정하는 메소드
	public void update(UsersDto dto) {
		log.info("Users의 update() 메소드 실행");
		if (dto.getUserNum() != this.userNum) {
			throw new IllegalArgumentException("정보 수정 실패! 유저의 고유 번호가 잘못되었습니다.");
		};
		// 유저 정보 수정
		if (dto.getUserEmail() != null) {
			this.userEmail = dto.getUserEmail();
		};
		if (dto.getUserPassword() != null) {
			this.userPassword = dto.getUserPassword();
		};
		this.userPhone = dto.getUserPhone();
		this.userGender = dto.getUserGender();
		this.userNickname = dto.getUserNickname();
	};
	
};
