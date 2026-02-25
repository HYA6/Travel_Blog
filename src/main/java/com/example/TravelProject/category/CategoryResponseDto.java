package com.example.TravelProject.category;

import com.example.TravelProject.category.entity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
// 카테고리 조회 및 응답용 DTO
public class CategoryResponseDto { // 응답용 DTO (서버 -> 클라이언트)
	
	private Long categoryId; // 카테고리 고유 번호
    @NotNull
	private String categoryName; // 카테고리 이름
    @NotNull
	private Category.CategoryVisibility categoryPrivate; // 카테고리 공개 여부
    private int categorySortOrder; // 카테고리 순서
    private List<CategoryResponseDto> categoryChildren; // 자식 카테고리 목록
    @NotNull
	private Long blogId; // 블로그 고유 번호
	
}