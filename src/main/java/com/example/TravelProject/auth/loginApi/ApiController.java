package com.example.TravelProject.auth.loginApi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TravelProject.auth.UsersDto;
import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ApiController {
	
	// NaverLoginBO
	@Autowired
	private NaverLoginBO naverLoginBO;

    // 네이버 로그인 성공 시 callback 호출 메소드
	@RequestMapping("/NaverCallback")
	public String callback(HttpSession session, @RequestParam String code, 
			@RequestParam String state) throws IOException, ParseException {
		
//		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
//
//		// 로그인 사용자 정보를 얻어온다.
//        String apiResult = naverLoginBO.getUserProfile(oauthToken);
//
//		// String 형식인 로그인 사용자 정보를 json 형태로 바꾼다.
//        // ✅ Jackson 파싱
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(apiResult);
//        JsonNode responseNode = rootNode.get("response");
//
//        String id = responseNode.get("id").asText();
//        String name = responseNode.get("name").asText();
//        String email = responseNode.get("email").asText();
//
//        String birthYear = responseNode.get("birthyear").asText();
//        String birthDay = responseNode.get("birthday").asText(); // MM-DD
//
//        LocalDate birthDate = LocalDate.parse(birthYear + "-" + birthDay);
//        int age = Period.between(birthDate, LocalDate.now()).getYears();
//
//		UsersDto usersDto = new UsersDto();
//		usersDto.setUserId(id);
//		usersDto.setUserName(name);
//		usersDto.setUserEmail(email);
//		usersDto.setUserBirthday(birthDate);
//
//		// 파싱된 값을 세션에 저장한다.
//		session.setAttribute("usersDto", usersDto);
		
		return "redirect:usersInsert";
	}
}
