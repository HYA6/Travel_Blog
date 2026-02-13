package com.example.TravelProject.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // csrf: 사이트 간 요청 위조 공격 방지 장치
                .csrf(csrf -> csrf.disable())
                /*
                    authorizeHttpRequests: URL 접근 권한 규칙을 정하는 곳
                        .anyRequest().permitAll()    // 모든 인가 허용
                        .anyRequest().authenticated()   // 허가된 인가만 허용
                    ex) .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                        - /admin → 관리자만
                        - 그 외 → 로그인 필요
                */
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        "/",
                                        "/login",
                                        "/join",
                                        "/users/join",
                                        "/assets/**",
                                        "/images/**"
                                ).permitAll()
                                .requestMatchers("/create/**").authenticated()
                                .anyRequest().authenticated()
                )
                /*
                    formLogin: security 로그인 사용 여부
                        .loginPage("/login.html")   // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/home")     // 로그인 성공 후 이동 페이지
                        .failureUrl("/login.html?error=true")   // 로그인 실패 후 이동 페이지
                        .usernameParameter("username")  // 아이디 파라미터명 설정
                        .passwordParameter("password")  // 패스워드 파라미터명 설정
                        .loginProcessingUrl("/login")   // 로그인 Form Action Url
                        .successHandler(loginSuccessHandler())  // 로그인 성공 후 핸들러
                        .failureHandler(loginFailureHandler())  // 로그인 실패 후 핸들러
                        .disable()  // security 로그인 사용X
                */
                .formLogin(form -> form
                    .loginPage("/login") // 사용자 정의 로그인 페이지
                    .usernameParameter("username")  // 아이디 파라미터명 설정
                    .passwordParameter("password")  // 패스워드 파라미터명 설정
                    .loginProcessingUrl("/login") // 로그인 Form Action Url
                    .permitAll()
                );

        return http.build();
    }
}
