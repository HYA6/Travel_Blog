package com.example.TravelProject.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@Service
public class JoinService {

    // 로그인 경로 확인, DB 형식에 맞게 변환
    public UsersDto joinChk(HttpSession session, UsersDto usersDto) {
        log.info("JoinService의 loginChk() 메소드");
        Date nowDate = new Date();

        if (usersDto.getUserId() == null) {
            usersDto = (UsersDto) session.getAttribute("usersDto"); // form 사용X (로그인 API 이용)
        } else {
            // form으로 넘어온 생일 데이터로 나이 저장 (회원가입 이용)
            int birthYear = Integer.parseInt(usersDto.getUserBirhtday().substring(0,4));
            @SuppressWarnings("deprecation")
            int year = nowDate.getYear() + 1900;
            usersDto.setUserAge(year - birthYear);
        }

        // 생성일에 오늘 날짜 넣어주기(yyyy-MM-dd)
        usersDto.setUserCreateDate(nowDate);

        return usersDto;
    };
}