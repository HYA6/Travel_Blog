package com.example.TravelProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TravelProject.dto.PostVisitDto;
import com.example.TravelProject.entity.Post;
import com.example.TravelProject.entity.PostVisit;
import com.example.TravelProject.entity.Users;
import com.example.TravelProject.repository.PostRepository;
import com.example.TravelProject.repository.PostVisitRepository;
import com.example.TravelProject.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostVisitService {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostVisitRepository postVisitRepository;
	
	// 조회수 올리는 여부
	public String findDate(Long userNum, Long postId) {
		// 유저 번호 없으면 저장 못함
		usersRepository.findById(userNum).orElseThrow(() -> new IllegalArgumentException("조회수 찾기 실패! 대상 유저가 없습니다."));
		// 게시글 번호 없으면 저장 못함
		postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("조회수 찾기 실패! 대상 게시글이 없습니다."));
		String visitDate = "";
		try { visitDate = postVisitRepository.findDate(userNum, postId); } catch (Exception e) { }
		return visitDate;
	};
	
	// 조회수 올리기
	public void visitUp(PostVisitDto postVisitDto) {
		log.info("PostVisitService의 visitUp()");
		// 유저 번호 없으면 저장 못함
		Users users = usersRepository.findById(postVisitDto.getUserNum())
				.orElseThrow(() -> new IllegalArgumentException("조회수 올리기 실패! 대상 유저가 없습니다."));
		// 게시글 번호 없으면 저장 못함
		Post post = postRepository.findById(postVisitDto.getPostId())
				.orElseThrow(() -> new IllegalArgumentException("조회수 올리기 실패! 대상 게시글이 없습니다."));
		// entity로 변환
		PostVisit postVisit = PostVisit.toEntity(postVisitDto, users, post);
		postVisitRepository.save(postVisit);
	};
	
	// 조회수 찾기
	public int findVisit(Long postId) {
		log.info("PostVisitService의 findVisit()");
		// 게시글 번호 없으면 저장 못함
		postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("조회수 찾기 실패! 대상 게시글이 없습니다."));
		int visitCount = postVisitRepository.findByPostId(postId);
		return visitCount;
	};
	
};
