package com.example.TravelProject.post.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.example.TravelProject.auth.entity.Users;
import com.example.TravelProject.post.PostVisitDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostVisit implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="visit_id", nullable=false, columnDefinition="int")
	private Long postVisitId; // 게시글 조회수 고유 번호
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
	public static PostVisit toEntity(PostVisitDto postVisitDto, Users users, Post post) {
		// Entity 생성 및 반환
		return new PostVisit(postVisitDto.getPostVisitId(), postVisitDto.getPostVisitDate(), 
				users, post);
	};
	
};
