package com.example.TravelProject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelProject.auth.UsersService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JoinApiController {
	
	@Autowired
	private UsersService usersService;
	
	// 아이디 중복 조회 fetch
	@GetMapping("/api/usersIdCheck/{userId}")
	public ResponseEntity<String> usersIdCheck(@PathVariable String userId) {
		log.info("JoinApiController의 usersId() 메소드 실행");
		String id = "";
		try {
			id = usersService.usersId(userId);
		} catch (Exception e) {
			id = null;
		}
		return id == null ?
				ResponseEntity.status(HttpStatus.OK).body(id) :
				ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	};

	// 이메일 중복 확인 fetch
	@GetMapping("api/usersEmailCheck/{userEmail}")
	public ResponseEntity<String> usersEmailCheck(@PathVariable String userEmail) {
		log.info("JoinController의 usersEmailCheck() 메소드");
		String email = "";
        try {
            email = usersService.usersEmail(userEmail);
        } catch (Exception e) {
            email = null;
        }
		return email == null ?
                ResponseEntity.status(HttpStatus.OK).body(email) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	};
	
};
