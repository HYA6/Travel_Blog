package com.example.TravelProject.category;

import java.util.List;
import java.util.stream.Collectors;

import com.example.TravelProject.category.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TravelProject.blog.entity.Blog;
import com.example.TravelProject.blog.BlogRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryService {
	
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;

    // 검증용 메소드(수정하려는 카테고리의 블로그가 같은지) -> 조회 및 삭제 메소드에서도 사용하는지 보고 사용 안하면 createCategory에 포함
    private void compare(Category parent, Long blogId) {
        log.info("CategoryService의 compare() 메소드 실행");
        // 부모 카테고리가 존재하지만 블로그가 같지 않다면 예외 발생
        if (parent != null && !parent.getBlog().getBlogId().equals(blogId)) {
            throw new IllegalArgumentException("다른 블로그의 카테고리는 생성 및 수정, 삭제할 수 없음");
        }
    }
	
	// 카테고리 생성 및 수정
	@Transactional
	public void createCategory(CategoryRequestDto dto, Long blogId) {
		log.info("CategoryService의 createCategory() 메소드 실행");
        Category parent = null;

        // 부모 카테고리 고유 번호가 있는데 DB에는 데이터가 없을 경우 예외 처리
        if (dto.getParentId() != null) {
            parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리 없음"));
        }

        // 생성 및 수정 전 검증
        compare(parent, blogId);

        // 카테고리 Entity 객체
        Category category;
        // 블로그 Entity 객체
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new IllegalArgumentException("블로그 없음"));

        // 카테고리 고유 번호가 없으면 새로 매핑, 있으면 수정
        if (dto.getCategoryId() == null) {
			// dto를 entity로 변환
            category = CategoryMapper.toEntity(dto, parent, blog);
        } else {
            // 수정하려는 카테고리의 고유 번호로 카테고리 Entity 얻어오기 (없으면 예외 발생)
            category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("카테고리 없음"));
			// 카테고리 수정
			category.update(dto);
        }

        // 카테고리 생성 및 수정
        categoryRepository.save(category);
	}
	
	// 블로그 고유 번호로 카테고리 목록 찾기
	@Transactional
	public List<CategoryDto> selectCategoryList(Long blogId) {
		log.info("CategoryService의 selectCategoryList() 메소드 실행");
		// stream 사용
		return categoryRepository.selectByBlog(blogId)
				.stream()
				.map(category -> CategoryDto.toDto(category)) // entity를 dto로 변환
				.collect(Collectors.toList());
	};
	
	// 카테고리 옵션들로 카테고리 한 건 찾기
	@Transactional
	public CategoryDto selectCategory(CategoryDto dto) {
		log.info("CategoryService의 selectCategoryById() 메소드 실행");
		// 카테고리를 찾으려는 블로그가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Blog blog = blogRepository.findById(dto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("카테고리 찾기 실패! 대상 블로그가 없습니다."));
		// dto를 entity로 변환
		Category entity = Category.toEntity(dto, blog);
		// 카테고리 찾기
		Category category = categoryRepository.selectByOption(entity.getCategoryGup(), entity.getCategoryLev(), 
					entity.getCategorySeq(), entity.getBlog().getBlogId());
//		log.info("category: {}", category);
		return category != null ? CategoryDto.toDto(category) : null;
	};
	
	// 카테고리 고유 번호로 카테고리 한 건 찾기
	@Transactional
	public CategoryDto selectCategoryById(CategoryDto dto) {
		log.info("CategoryService의 selectCategoryById() 메소드 실행");
		// 카테고리를 찾으려는 블로그가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Blog blog = blogRepository.findById(dto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("카테고리 찾기 실패! 대상 블로그가 없습니다."));
		// dto를 entity로 변환
		Category entity = Category.toEntity(dto, blog);
		// 카테고리 찾기
		Category category = categoryRepository.findById(entity.getCategoryId()).orElse(null);
//		log.info("category: {}", category);
		return CategoryDto.toDto(category);
	};
	
	// 카테고리 삭제
	@Transactional
	public void deleteCategory(CategoryDto dto) {
		log.info("CategoryService의 deleteCategory() 메소드 실행");
		// 카테고리를 찾으려는 블로그가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Blog blog = blogRepository.findById(dto.getBlogId())
				.orElseThrow(() -> new IllegalArgumentException("카테고리 찾기 실패! 대상 블로그가 없습니다."));
		// dto를 entity로 변환
		Category entity = Category.toEntity(dto, blog);
		log.info("{}", entity.getCategoryId());
		if (entity.getCategoryId() == null || entity.getCategoryId() == 0) {
			// 메인 카테고리면
			categoryRepository.deleteMain(entity.getCategoryGup());
		} else {
			// 서브 카테고리면
			categoryRepository.deleteById(entity.getCategoryId());
		};
	};

};