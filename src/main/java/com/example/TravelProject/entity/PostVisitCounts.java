package com.example.TravelProject.entity;

import java.io.Serializable;
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

@SuppressWarnings("serial")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class PostVisitCounts implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="visit_id", nullable=false, columnDefinition="int")
	private Long postVisitId; // 게시글 조회수 고유 번호
	@Column(name="visit_count", columnDefinition="int")
	private String postVisitCount; // 게시글 조회수
	@Column(name="visit_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date postVisitDate; // 게시글 조회한 날짜
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false, columnDefinition="int")
	private Users users; // 유저 고유 번호
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "post_id", nullable=false, columnDefinition="int")
	private Post post; // 게시글 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드
	public static PostVisitCounts toEntity(PostVisitCounts postVisitCounts, Users users, Post post) {
		log.info("PostVisitCounts의 toEntity() 메소드 실행");
		// Entity 생성 및 반환
		return new PostVisitCounts(postVisitCounts.getPostVisitId(), postVisitCounts.getPostVisitCount(),
				postVisitCounts.getPostVisitDate(), users, post);
	};
	
};
