package com.example.TravelProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.TravelProject.dto.PostImagesDto;

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
@Table(name="POSTIMAGES")
public class PostImages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="image_id", nullable=false, columnDefinition="int")
	private Long postImageId; // 게시글 이미지 고유 번호
	@Column(name="image_name", nullable=false, columnDefinition="varchar(100)")
	private String postImageName; // 게시글 이미지 이름
	@Column(name="image_path", nullable=false, columnDefinition="varchar(100)")
	private String postImagePath; // 게시글 이미지 경로
	@Column(name="image_gup", columnDefinition="int")
	private int postImageGup; // 게시글 이미지 그룹
	@Column(name="image_seq", columnDefinition="int")
	private int postImageSeq; // 게시글 이미지 출력 순서
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "post_id", nullable=false, columnDefinition="int")
	private Post post; // 게시글 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(블로그, 사용자)
	public static PostImages toEntity(PostImagesDto postImagesDto, Post post) {
		log.info("Postimages의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new PostImages(postImagesDto.getPostImageId(), postImagesDto.getPostImageName(), 
				postImagesDto.getPostImagePath(), postImagesDto.getPostImageGup(), 
				postImagesDto.getPostImageSeq(), post);
	};
	
};
