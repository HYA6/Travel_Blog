package com.example.TravelProject.security.service;

import com.example.TravelProject.auth.UsersRepository;
import com.example.TravelProject.auth.entity.Users;
import com.example.TravelProject.security.data.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // security가 인식할 수 있는 모델로 변환
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("CustomUserDetailsService의 loadUserByUsername() 메소드 실행");
        Users user = usersRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디의 사용자를 찾을 수 없습니다: " + userId));

        return new CustomUserDetails(user);
    }
}
