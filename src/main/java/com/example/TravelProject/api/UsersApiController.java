package com.example.TravelProject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelProject.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UsersApiController {
	
	@Autowired
	private UsersService usersService;
	
	// 유저 정보 삭제
	@GetMapping("/api/myInfoDelete/{users_num}")
	public ResponseEntity<Long> deleteUsers(@PathVariable Long users_num) {
		log.info("UsersApiController의 deleteUsers() 메소드 실행");
		// 유저 정보 삭제하기
		usersService.deleteUsers(users_num);
		return ResponseEntity.status(HttpStatus.OK).body(users_num);
	}
	
}
