package com.example.TravelProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.TravelProject.dto.BlogDto;

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
public class Blog {

//	기본키로 사용할 필드 선언
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="blog_id", nullable=false, columnDefinition="int")
	private Long blogId; // 블로그 고유 번호
	@Column(name="blog_name", nullable=false, columnDefinition="varchar(20)")
	private String blogName; // 블로그 이름
	@Column(name="blog_thema", nullable=false, columnDefinition="varchar(200)")
	private String blogThema; // 블로그 테마
//	데이터를 저장할 필드 선언
	@Column(name="blog_url", nullable=false, columnDefinition="varchar(100)")
	private String blogUrl; // 블로그 주소
	// 외래키
	@OneToOne // 1:1 단방향 관계
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false, columnDefinition="int") // user_num 컬럼에 Users의 대표값(기본키)을 저장한다.
	private Users users; // 유저 고유 번호
	
	
	// DTO 데이터를 Entity로 변환하는 메소드
	public static Blog toEntity(BlogDto dto, Users users) {
		log.info("Blog의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new Blog(dto.getBlogId(), dto.getBlogName(), dto.getBlogThema(), dto.getBlogUrl(), users);
	};
	
	// 블로그를 수정하는 메소드
	public void update(BlogDto dto) {
		log.info("Blog의 update() 메소드 실행");
		// 블로그를 수정하기 위해 요청한 id가 데이터베이스에 저장된 id와 다를 경우 예외를 발생시킨다.
		if (dto.getBlogId() != this.blogId) {
			throw new IllegalArgumentException("블로그 수정 실패! 블로그의 id가 잘못되었습니다.");
		};
		// 블로그 수정
		if (dto.getBlogName() != null) {
			this.blogName = dto.getBlogName();
		};
		if (dto.getBlogThema() != null) {
			this.blogThema = dto.getBlogThema();
		};
		if (dto.getBlogUrl() != null) {
			this.blogUrl = dto.getBlogUrl();
		};
	};
	
};
