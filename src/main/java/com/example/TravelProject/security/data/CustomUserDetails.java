package com.example.TravelProject.security.data;

import com.example.TravelProject.auth.entity.Users;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// security 전용 DTO 느낌
@Getter
public class CustomUserDetails implements UserDetails {

    // security용 데이터
    private final Long userNum;
    private final String userId;
    private final String userPassword;
    private final String role;

    // 생성자
    public CustomUserDetails(Users user) {
        this.userNum = user.getUserNum();
        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.role = user.getUserRole().name();
    }

    // 시큐리티 권한 설정
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.userId;
    }
    @Override
    public String getPassword() {
        return this.userPassword;
    }

    // 계정 활성화 여부
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}