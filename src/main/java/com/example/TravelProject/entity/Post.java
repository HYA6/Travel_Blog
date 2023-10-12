package com.example.TravelProject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id", nullable=false, columnDefinition="int")
	private Long postId; // 게시글 고유 번호
	@Column(name="post_form", nullable=false, columnDefinition="varchar(10)")
	private String postForm; // 게시글 양식
	@Column(name="post_strat_date", nullable=false, columnDefinition="varchar(10)")
	private String postStratDate; // 게시글 여행 첫 날짜
	@Column(name="post_end_date", nullable=false, columnDefinition="varchar(10)")
	private String postEndDate; // 게시글 여행 마지막 날짜
	@Column(name="post_place", nullable=false, columnDefinition="varchar(100)")
	private String postPlace; // 게시글 여행 장소
	@Column(name="post_subject", nullable=false, columnDefinition="varchar(100)")
	private String postSubject; // 게시글 제목
	@Column(name="post_tag", columnDefinition="varchar(20)")
	private String postTag; // 게시글 태그
	@Column(name="post_write", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date postWrite; // 게시글 작성일
	@Column(name="post_update")
	@Temporal(TemporalType.DATE)
	private Date postUpdate; // 게시글 수정일
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "blog_id", nullable=false, columnDefinition="int")
	private Blog blog; // 블로그 고유 번호
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "category_id", nullable=false, columnDefinition="int")
	private Category category; // 카테고리 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static Post toEntity(Post post, Blog blog, Category category) {
		log.info("Post의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new Post(post.getPostId(), post.getPostForm(), post.getPostStratDate(), post.getPostEndDate(), post.getPostPlace(), 
				post.getPostSubject(), post.getPostTag(), post.getPostWrite(), post.getPostUpdate(), blog, category);
	};
	
};
