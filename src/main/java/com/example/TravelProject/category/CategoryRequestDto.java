package com.example.TravelProject.category;

import com.example.TravelProject.category.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
// 카테고리 생성 및 수정용 DTO
public class CategoryRequestDto { // 요청용 DTO (클라이언트 -> 서버)
	
	private Long categoryId; // 카테고리 고유 번호
    @NotBlank(message = "카테고리 제목을 입력해주세요.")
	private String categoryName; // 카테고리 이름
    @NotNull(message = "카테고리 공개 여부를 선택해주세요.")
	private Category.CategoryVisibility categoryPrivate; // 카테고리 공개 여부
    private int categorySortOrder; // 카테고리 순서
    private Long parentId; // 부모 카테고리 고유 번호

}