package com.example.TravelProject.loginApi;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TravelProject.dto.UsersDto;
import com.github.scribejava.core.model.OAuth2AccessToken;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ApiController {
	
	// NaverLoginBO
	@Autowired
	private NaverLoginBO naverLoginBO;
	private String apiResult;

	// 네이버 로그인 성공 시 callback 호출 메소드
	@RequestMapping("/NaverCallback")
	public String callback(HttpSession session, @RequestParam String code, 
			@RequestParam String state) throws IOException, ParseException, org.json.simple.parser.ParseException {
		log.info("ApiController 컨트롤러의 NaverCallback() 메소드 실행");
		
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		// 로그인 사용자 정보를 얻어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
//		log.info("apiResult: {}", apiResult);
		
		// String 형식인 로그인 사용자 정보를 json 형태로 바꾼다.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		// top 레벨 단계 데이터 파싱 - response
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
//		log.info("response_obj: {}", response_obj);
		String id = (String) response_obj.get("id"); // 아이디
//		log.info("id: {}", id);
		String name = (String) response_obj.get("name"); // 이름
//		log.info("name: {}", name);
		Date date = new Date();
		@SuppressWarnings("deprecation")
		int year = date.getYear() + 1900;
		String birthyear = (String) response_obj.get("birthyear");
		int age = year - Integer.parseInt(birthyear); // 나이
//		log.info("age: {}", age);
		String email = (String) response_obj.get("email"); // 이메일
//		log.info("email: {}", email);
		String birthmonthday = (String) response_obj.get("birthday");
//		log.info("birthmonthday: {}", birthmonthday);
		String birthday = birthyear + "-" + birthmonthday; // 생일(yyyy-MM-DD)
//		log.info("birthday: {}", birthday);
		
		UsersDto usersDto = new UsersDto();
		usersDto.setUserId(id);
		usersDto.setUserName(name);
		usersDto.setUserAge(age);
		usersDto.setUserEmail(email);
		usersDto.setUserBirhtday(birthday);
		
//		log.info("usersDto: {}", usersDto);
		
		// 파싱된 값을 세션에 저장한다.
		session.setAttribute("usersDto", usersDto);
		
		return "redirect:usersInsert";
	}
	

}
