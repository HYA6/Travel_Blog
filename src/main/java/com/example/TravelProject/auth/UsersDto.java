package com.example.TravelProject.auth;

import java.time.LocalDate;

import com.example.TravelProject.auth.entity.Users;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsersDto {
	
	private Long userNum; // 유저 고유 번호

    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String userName;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String userEmail;

    @Past(message = "생년월일은 과거 날짜여야 합니다.")
    @NotNull(message = "생년월일을 선택해주세요.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate userBirthday;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPassword;

    @Pattern(
            regexp = "^$|^0\\d{1,2}-\\d{3,4}-\\d{4}$",
            message = "전화번호 형식이 올바르지 않습니다"
    )
	private String userPhone; // 유저 전화번호

	private String userGender; // 유저 성별
	private String userNickname; // 유저 닉네임
	private LocalDate userCreateDate; // 유저 생성일
	
	// entity를 dto로 변환하는 메소드
	public static UsersDto toDto(Users users) {
		return new UsersDto(users.getUserNum(), users.getUserId(), users.getUserName(),
				users.getUserEmail(), users.getUserBirthday(), users.getUserPassword(), users.getUserPhone(),
				users.getUserGender(), users.getUserNickname(), users.getUserCreateDate());
	}
	
}