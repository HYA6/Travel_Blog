package com.example.TravelProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.entity.Users;
import com.example.TravelProject.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	// 유저 고유 번호로 로그인한 유저 정보 가져오기
	public UsersDto selectIUser(Long userNum) {
		log.info("UsersService의 selectIUser() 메소드 실행");
		Users users = usersRepository.findById(userNum).orElse(null);
//		log.info("users: {}", users);
		// Entity 데이터를 Dto로 바꿔주기
		return users != null ? UsersDto.toDto(users) : null;
	};
	
	// 유저 아이디로 로그인한 유저 정보 가져오기
	public UsersDto findByuserId(String userId) {
		log.info("UsersService의 findByuserId() 메소드 실행");
		Users users = null;
		try {
			users = usersRepository.findByuserId(userId);
		} catch (NullPointerException e) { }
//		log.info("users: {}", users);
		// Entity 데이터를 Dto로 바꿔주기
		return users != null ? UsersDto.toDto(users) : null;
	};
	
	// 유저 이메일로 로그인한 유저 정보 가져오기
	public UsersDto findByuserEmail(String userEmail) {
		log.info("UsersService의 findByuserEmail() 메소드 실행");
		Users users = null;
		try {
			users = usersRepository.findByuserEmail(userEmail);
		} catch (NullPointerException e) { }
//		log.info("users: {}", users);
		// Entity 데이터를 Dto로 바꿔주기
		return users != null ? UsersDto.toDto(users) : null;
	};
	
	// 유저 정보 저장
	public void saveUser(UsersDto usersDto) {
		log.info("UsersService의 findByuserEmail() 메소드 실행");
		// Dto를 Entity로 변환
		Users users = Users.toEntity(usersDto);
		// 유저 정보 저장
		usersRepository.save(users);
		// Entity 데이터를 Dto로 바꿔주기
	};
	
	// 아이디 중복 조회
	public String usersId(String userId) {
		log.info("UsersService의 usersId() 메소드 실행");
		// 넘어온 아이디가 같은게 있는지 확인
		Users users = usersRepository.findByuserId(userId);
		return users.getUserId();
	};
	
	// 유저 정보 수정
	public UsersDto updateInfo(UsersDto usersDto) {
		log.info("UsersService의 updateInfo() 메소드 실행");
//		log.info("usersDTO: {}", usersDTO);
		// 수정하려는 유저 정보가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Users users = usersRepository.findById(usersDto.getUserNum())
				.orElseThrow(() -> new IllegalArgumentException("유저 정보 수정 실패! 대상 유저가 없습니다."));
		// 유저 정보 수정, 유저 정보 갱신
		users.update(usersDto);
		// 수정된 유저 정보로 다시 저장한다.
		Users updated = usersRepository.save(users);
		return UsersDto.toDto(updated);
	};
	
	// 유저 정보 삭제
	public void deleteUsers(Long userNum) {
		log.info("UsersService의 deleteUsers() 메소드 실행");
		usersRepository.deleteById(userNum);
	};
	
};