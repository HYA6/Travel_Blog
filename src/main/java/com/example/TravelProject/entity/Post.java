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

import com.example.TravelProject.dto.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id", nullable=false, columnDefinition="int")
	private Long postId; // 게시글 고유 번호
	@Column(name="post_form", nullable=false, columnDefinition="varchar(10)")
	private String postForm; // 게시글 양식
	@Column(name="post_start_date", nullable=false, columnDefinition="varchar(10)")
	private String postStartDate; // 게시글 여행 첫 날짜
	@Column(name="post_end_date", nullable=false, columnDefinition="varchar(10)")
	private String postEndDate; // 게시글 여행 마지막 날짜
	@Column(name="post_place", nullable=false, columnDefinition="varchar(100)")
	private String postPlace; // 게시글 여행 장소
	@Column(name="post_subject", nullable=false, columnDefinition="varchar(100)")
	private String postSubject; // 게시글 제목
	@Column(name="post_tag", columnDefinition="varchar(100)")
	private String postTag; // 게시글 태그
	@Column(name="post_thumbnail", columnDefinition="varchar(100)")
	private String postThumbnail; // 게시글 대표 이미지 이름
	@Column(name="post_write", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date postWrite; // 게시글 작성일
	@Column(name="post_update")
	@Temporal(TemporalType.DATE)
	private Date postUpdate; // 게시글 수정일
	@Column(name="post_visits", columnDefinition="int default 0")
	private int postVisits; // 게시글 조회수
	@Column(name="post_likes", columnDefinition="int default 0")
	private int postLikes; // 게시글 좋아요 수
	@Column(name="post_comments", columnDefinition="int default 0")
	private int postComments; // 게시글 전체 댓글 수
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
	public static Post toEntity(PostDto postDto, Blog blog, Category category) {
		// Entity 생성 및 반환
		return new Post(postDto.getPostId(), postDto.getPostForm(), postDto.getPostStartDate(), postDto.getPostEndDate(), 
				postDto.getPostPlace(), postDto.getPostSubject(), postDto.getPostTag(), postDto.getPostThumbnail(), 
				postDto.getPostWrite(), postDto.getPostUpdate(), postDto.getPostVisits(), postDto.getPostLikes(),
				postDto.getPostComments(), blog, category);
	};
	
};
