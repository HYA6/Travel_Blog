package com.example.TravelProject.category.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.example.TravelProject.blog.entity.Blog;
import com.example.TravelProject.category.CategoryDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id", nullable=false)
	private Long categoryId; // 카테고리 고유 번호
	@Column(name="category_name", nullable=false, length = 100)
	private String categoryName; // 카테고리 이름
	@Column(name="category_private", nullable=false, length = 10)
	private String categoryPrivate; // 카테고리 공개 여부
	@Column(name="category_gup")
	private int categoryGup; // 카테고리 그룹(상위 카테고리 구분)
	@Column(name="category_lev")
	private int categoryLev; // 카테고리 레벨(상,하위 카테고리 구분)
	@Column(name="category_seq")
	private int categorySeq; // 카테고리 출력 순서(카테고리 내의 출력 순서)
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "blog_id", nullable=false) // blog_id 컬럼에 Blog의 대표값(기본키)을 저장한다.
	private Blog blog; // 블로그 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static Category toEntity(CategoryDto dto, Blog blog) {
		// Entity 생성 및 반환
		return new Category(dto.getCategoryId(), dto.getCategoryName(), dto.getCategoryPrivate(),
				dto.getCategoryGup(), dto.getCategoryLev(), dto.getCategorySeq(), blog);
	};
	
	// 카테고리를 수정하는 메소드
	public void update(CategoryDto dto) {
		log.info("Blog의 update() 메소드 실행");
		// 카테고리를 수정하기 위해 요청한 블로그 고유 번호가 데이터베이스에 저장된 블로그 고유 번호와 다를 경우 예외를 발생시킨다.
		if (dto.getBlogId() != this.blog.getBlogId()) {
			throw new IllegalArgumentException("카테고리 수정 실패! 블로그의 고유 번호가 잘못되었습니다.");
		};
		// 카테고리를 수정하기 위해 요청한 카테고리 고유 번호가 데이터베이스에 저장된 카테고리 고유 번호와 다를 경우 예외를 발생시킨다.
		if (dto.getCategoryId() != this.categoryId) {
			throw new IllegalArgumentException("카테고리 수정 실패! 카테고리의 고유 번호가 잘못되었습니다.");
		};
		// 카테고리 수정
		if (dto.getCategoryName() != null) {
			this.categoryName = dto.getCategoryName();
		};
		if (dto.getCategoryPrivate() != null) {
			this.categoryPrivate = dto.getCategoryPrivate();
		};
		this.categoryGup = dto.getCategoryGup();
		this.categoryLev = dto.getCategoryLev();
		this.categorySeq = dto.getCategorySeq();
	};
	
};
