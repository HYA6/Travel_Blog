package com.example.TravelProject.dto;

import com.example.TravelProject.entity.Category;

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
public class CategoryDto {
	
	private Long categoryId; // 카테고리 고유 번호
	private String categoryName; // 카테고리 이름
	private String categoryPrivate; // 카테고리 공개 여부
	private int categoryGup; // 카테고리 그룹(상위 카테고리 구분)
	private int categoryLev; // 카테고리 레벨(하위 카테고리 구분)
	private int categorySeq; // 카테고리 출력 순서(카테고리 내의 출력 순서)
	private Long blogId; // 블로그 고유 번호
	
	// entity를 dto로 변환하는 메소드
	public static CategoryDto toDto(Category category) {
		return new CategoryDto(category.getCategoryId(), category.getCategoryName(), category.getCategoryPrivate(), 
				category.getCategoryGup(), category.getCategoryLev(), category.getCategorySeq(), category.getBlog().getBlogId());
	};
	
};