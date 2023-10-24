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

import com.example.TravelProject.dto.PostTextsDto;

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
public class PostTexts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="text_id", nullable=false, columnDefinition="int")
	private Long postTextId; // 게시글 내용 고유 번호
	@Column(name="text", nullable=false, columnDefinition="varchar(1000)")
	private String postText; // 게시글 내용
	@Column(name="text_gup", columnDefinition="int")
	private int postTextGup; // 게시글 내용 그룹
	@Column(name="post_seq", columnDefinition="int")
	private int postTextSeq; // 게시글 내용 출력 순서
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "post_id", nullable=false, columnDefinition="int")
	private Post post; // 게시글 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static PostTexts toEntity(PostTextsDto postTextsDto, Post post) {
		log.info("PostTexts의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new PostTexts(postTextsDto.getPostTextId(), postTextsDto.getPostText(), 
				postTextsDto.getPostTextGup(), postTextsDto.getPostTextSeq(), post);
	};
	
};
