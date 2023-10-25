package com.example.TravelProject.dto;

import java.util.Date;

import com.example.TravelProject.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsersDto {
	
	private Long userNum; // 유저 고유 번호
	private String userId; // 유저 아이디
	private String userName; // 유저 이름
	private int userAge; // 유저 나이
	private String userEmail; // 유저 이메일
	private String userBirhtday; // 유저 생일
	private String userPassword; // 유저 비번
	private String userPhone; // 유저 전화번호
	private String userGender; // 유저 성별
	private String userNickname; // 유저 닉네임
	private Date userCreateDate; // 유저 생성일
	
	// entity를 dto로 변환하는 메소드
	public static UsersDto toDto(Users users) {
		return new UsersDto(users.getUserNum(), users.getUserId(), users.getUserName(), users.getUserAge(),
				users.getUserEmail(), users.getUserBirhtday(), users.getUserPassword(), users.getUserPhone(), 
				users.getUserGender(), users.getUserNickname(), users.getUserCreateDate());
	}
	
}