package com.example.TravelProject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TravelProject.dto.UsersDto;
import com.example.TravelProject.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JoinApiController {
	
	@Autowired
	private UsersService usersService;
	
	// 아이디 중복 조회 fetch
	@GetMapping("/api/idCheck/{users_id}")
	public ResponseEntity<String> usersId(@PathVariable String users_id) {
		log.info("JoinApiController의 usersId() 메소드 실행");
		String id = "";
		try {
			id = usersService.usersId(users_id);
		} catch (Exception e) {
			id = null;
		}
		return id == null ?
				ResponseEntity.status(HttpStatus.OK).body(id) :
				ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	};

	// 이메일 중복 확인 ajax
	@ResponseBody
	@RequestMapping("api/usersEmailCheck")
	public String usersEmailCheck(UsersDto usersDto) {
		log.info("JoinController의 usersEmailCheck() 메소드");
		String email = null;
//		log.info("usersDto.getUsers_email(): {}", usersDto.getUsers_email());
		UsersDto dto = usersService.findByuserEmail(usersDto.getUserEmail());
		try { // null이 넘어올 수 있기 때문에 예외 처리해준다.
			email = dto.getUserEmail();
		} catch (NullPointerException e) { }
//		log.info("email: {}", email);
		
		return email;
	};
	
};
