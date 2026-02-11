package com.example.TravelProject.auth;

import com.example.TravelProject.auth.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

@Slf4j
@Service
public class JoinService {

    // 로그인 경로 확인, DB 형식에 맞게 변환
    public UsersDto joinChk(HttpSession session, UsersDto usersDto) {
        log.info("JoinService의 loginChk() 메소드");

        if (usersDto.getUserId() == null) {
            usersDto = (UsersDto) session.getAttribute("usersDto"); // form 사용X (로그인 API 이용)
        }
        
        // 권한 설정 (회원가입 -> user)
        usersDto.setUserRole(Users.Role.USER);

        // 생성일
        LocalDate today = LocalDate.now();
        usersDto.setUserCreateDate(today);

        return usersDto;
    }
}