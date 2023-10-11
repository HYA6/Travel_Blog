package com.example.TravelProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class PostContents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="content_id", nullable=false, columnDefinition="int")
	private Long postContentId; // 게시글 내용 고유 번호
	@Column(name="content", nullable=false, columnDefinition="varchar(1000)")
	private String postContent; // 게시글 내용
	@Column(name="content_gup", columnDefinition="int")
	private int postContentGup; // 게시글 내용 그룹
	@Column(name="content_seq", columnDefinition="int")
	private int postContentSeq; // 게시글 내용 출력 순서
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "post_id", nullable=false, columnDefinition="int")
	private Post post; // 게시글 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static PostContents toEntity(PostContents postContents, Post post) {
		log.info("PostContents의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new PostContents(postContents.getPostContentId(), postContents.getPostContent(), 
				postContents.getPostContentGup(), postContents.getPostContentSeq(), post);
	};
	
};
