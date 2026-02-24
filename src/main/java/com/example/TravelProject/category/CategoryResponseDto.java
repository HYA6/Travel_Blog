package com.example.TravelProject.category;

import com.example.TravelProject.category.entity.Category;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CategoryResponseDto { // 응답용 DTO
	
	private Long categoryId; // 카테고리 고유 번호
	private String categoryName; // 카테고리 이름
	private Category.CategoryVisibility categoryPrivate; // 카테고리 공개 여부
    private int categorySortOrder; // 카테고리 순서
    private List<CategoryResponseDto> categoryChildren; // 자식 카테고리 목록
	private Long blogId; // 블로그 고유 번호
	
}