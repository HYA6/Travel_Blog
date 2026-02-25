package com.example.TravelProject.category.entity;

import com.example.TravelProject.category.CategoryRequestDto;
import jakarta.persistence.*;

import com.example.TravelProject.blog.entity.Blog;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"parent", "children", "blog"})
public class Category {

    // 공개 여부 enum
    public enum CategoryVisibility {
        PUBLIC,
        PRIVATE
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id", nullable=false)
	private Long categoryId; // 카테고리 고유 번호
	@Column(name="category_name", nullable=false, length = 100)
	private String categoryName; // 카테고리 이름
    @Enumerated(EnumType.STRING)
	@Column(name="category_private", nullable=false)
	private CategoryVisibility categoryPrivate; // 카테고리 공개 여부
    @Column(name = "category_sortOrder")
    private int categorySortOrder; // 같은 부모를 가진 카테고리들의 순서

    // 자기 참조 FK
    // 부모 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;
    // 자식 카테고리 (DB 컬럼 아님)
    @OneToMany(mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @OrderBy("categorySortOrder ASC")
    private List<Category> children = new ArrayList<>();

	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "blog_id", nullable=false) // blog_id 컬럼에 Blog의 대표값(기본키)을 저장한다.
	private Blog blog; // 블로그 고유 번호

    // 매개변수가 있는 생성자
    @Builder
    public Category(
            Long categoryId,
            String categoryName,
            CategoryVisibility categoryPrivate,
            int categorySortOrder,
            Category parent,
            List<Category> children,
            Blog blog) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryPrivate = categoryPrivate;
        this.categorySortOrder = categorySortOrder;
        this.parent = parent;
        this.children = children;
        this.blog = blog;
    }

    // 부모가 변경될 때 자식의 부모도 반영
    public void changeParent(Category parent) {
        this.parent = parent;
        parent.children.add(this);
    }
	
	// 카테고리를 수정하는 메소드
	public void update(CategoryRequestDto dto) {
		// 카테고리 이름, 공개여부 수정
		if (dto.getCategoryName() != null) this.categoryName = dto.getCategoryName();
		if (dto.getCategoryPrivate() != null) this.categoryPrivate = dto.getCategoryPrivate();
	}
}
