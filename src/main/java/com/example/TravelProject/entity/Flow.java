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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Flow {

//	기본키로 사용할 필드 선언
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flow_num", nullable=false, columnDefinition="int")
	private Long flowNum; // 팔로 고유 번호
//	데이터를 저장할 필드 선언
	@Column(name="flow_id", nullable=false, columnDefinition="varchar(100)")
	private String flowId; // 팔로 아이디
	@Column(name="flow_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date flowDate; // 팔로 날짜
	@Column(name="flow_status", columnDefinition="varchar(20)")
	private String flowStatus; // 팔로 상태
	@Column(name="flow_nickname", columnDefinition="varchar(10)")
	private String flowNickname; // 팔로 닉네임
	// 외래키
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_num", nullable=false, columnDefinition="int") // user_num 컬럼에 Users의 대표값(기본키)을 저장한다.
	private Users users; // 유저 고유 번호
	
	
//	DTO 데이터를 Entity로 변환하는 메소드(팔로, 사용자)
	public static Flow toEntity(Flow flow, Users users) {
		// Entity 생성 및 반환
		return new Flow(flow.getFlowNum(), flow.getFlowId(), flow.getFlowDate(), flow.getFlowStatus(), flow.getFlowNickname(), users);
	}
	
}
