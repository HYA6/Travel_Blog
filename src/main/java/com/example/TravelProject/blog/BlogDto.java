package com.example.TravelProject.blog;

import com.example.TravelProject.blog.entity.Blog;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogDto {
	
	private Long blogId; // 블로그 고유 번호

    @NotBlank(message = "블로그 제목을 입력해주세요.")
	private String blogName; // 블로그 이름

    @NotBlank(message = "블로그 테마를 선택해주세요.")
	private String blogThema; // 블로그 테마

    @NotBlank(message = "블로그 주소를 입력해주세요.")
    private String blogUrl; // 블로그 주소

    @NotBlank(message = "유저가 ")
	private Long userNum; // 유저 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static BlogDto toDto(Blog blog) {
		return new BlogDto(blog.getBlogId(), blog.getBlogName(), blog.getBlogThema(), 
				blog.getBlogUrl(), blog.getUsers().getUserNum());
	}
	
}