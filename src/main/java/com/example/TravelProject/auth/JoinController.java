package com.example.TravelProject.auth;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JoinController {

    @Autowired
    private JoinService joinService;
	@Autowired
	private UsersService usersService;
	
	// 아이디가 있는지 확인 후 유저 회원가입 및 로그인
    @PostMapping("/users/join")
    @ResponseBody
    public Map<String, Object> join(
            HttpSession session,
            @Valid UsersDto usersDto,
            BindingResult bindingResult) {

        // 회원가입 전처리 (소셜/일반 구분 등)
        usersDto = joinService.joinChk(session, usersDto);

        // validation error 뜨면 실행 (유효성 인증 실패)
        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("message", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }

        // DB 저장
        usersService.saveUser(usersDto);

        // 로그인 처리
        UsersDto savedUser = usersService.findByUserId(usersDto.getUserId());
        session.setAttribute("userNum", savedUser.getUserNum());

        result.put("success", true);
        return result;
    }
}
