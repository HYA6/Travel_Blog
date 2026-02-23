package com.example.TravelProject.auth;

import java.time.LocalDate;

import com.example.TravelProject.auth.entity.Users;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
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
    private Users.Role userRole; // 유저 권한
	private String userNickname; // 유저 닉네임
	private LocalDate userCreateDate; // 유저 생성일

    // Entity로 변환 (암호화된 비밀번호를 외부에서 주입받음)
    public Users toEntity(String encodedPassword) {
        return Users.builder()
                .userId(this.userId)
                .userName(this.userName)
                .userEmail(this.userEmail)
                .userBirthday(this.userBirthday)
                .userPassword(encodedPassword)
                .userPhone(this.userPhone)
                .userGender(this.userGender)
                .userRole(Users.Role.USER) // 기본 권한
                .userNickname(this.userNickname)
                .userCreateDate(LocalDate.now())
                .build();
    }
    // Entity → DTO
    public static UsersDto fromEntity(Users user) {
        return UsersDto.builder()
                .userNum(user.getUserNum())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userBirthday(user.getUserBirthday())
                .userPhone(user.getUserPhone())
                .userGender(user.getUserGender())
                .userNickname(user.getUserNickname())
                .build();
    }
	// entity를 dto로 변환하는 메소드
//	public static UsersDto toDto(Users users) {
//		return new UsersDto(users.getUserNum(), users.getUserId(), users.getUserName(),
//				users.getUserEmail(), users.getUserBirthday(), users.getUserPassword(), users.getUserPhone(),
//				users.getUserGender(), users.getUserRole(), users.getUserNickname(), users.getUserCreateDate());
//	}
	
}