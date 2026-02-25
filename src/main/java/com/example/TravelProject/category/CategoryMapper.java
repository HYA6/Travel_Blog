package com.example.TravelProject.category;

import com.example.TravelProject.category.entity.Category;

public class CategoryMapper {
    /*
        RequestDto를 통해 클라이언트의 요청으로 받은 dto 정보를 entity로 변경
        - parentCategoryId 같은 경우 null이 올 수도 있으니 null인지 확인 후 아닐 경우(자식 카테고리)에만 실행
    */
    // DTO -> Entity
    public static Category toEntity(CategoryRequestDto dto, Category parent) {
        return Category.builder()
                .categoryId(dto.getCategoryId())
                .categoryName(dto.getCategoryName())
                .categoryPrivate(dto.getCategoryPrivate())
                .categorySortOrder(dto.getCategorySortOrder())
                .parent(parent)
                .build();
    }

    // Entity -> DTO
    public static CategoryResponseDto fromEntity(Category category) {
        return CategoryResponseDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryPrivate(category.getCategoryPrivate())
                .categorySortOrder(category.getCategorySortOrder())
//                .categoryChildren()
                .blogId(category.getBlog().getBlogId())
                .build();
    }

}
