package com.example.TravelProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.TravelProject.dto.LikedPostDto;
import com.example.TravelProject.entity.LikedPost;
import com.example.TravelProject.entity.Post;
import com.example.TravelProject.entity.Users;
import com.example.TravelProject.repository.LikedPostRepository;
import com.example.TravelProject.repository.PostRepository;
import com.example.TravelProject.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LikedPostService {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private LikedPostRepository likedPostRepository;
	
	// 전체 좋아요 수 찾기
	public int selectAllLikes(Long postId) {
		log.info("LikedPostService의 selectAllLikes()");
		// 게시글 번호 없으면 찾지 못함
		postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("좋아요 수 찾기 실패! 대상 게시글이 없습니다."));
		// 전체 좋아요 수 찾기
		int likesCount = likedPostRepository.selectAllLikes(postId);
		return likesCount;
	};
	
	// 유저 번호와 게시글 번호로 좋아요 정보 찾기
	public LikedPostDto findliked(Long userNum, Long postId) {
		log.info("LikedPostService의 findliked()");
		// 유저 번호 없으면 찾지 못함
		usersRepository.findById(userNum).orElseThrow(() -> new IllegalArgumentException("좋아요 여부 찾기 실패! 대상 유저가 없습니다."));
		// 게시글 번호 없으면 찾지 못함
		postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("좋아요 여부 찾기 실패! 대상 게시글이 없습니다."));
		// 좋아요 정보 찾기
		LikedPost likedPost = likedPostRepository.findLiked(userNum, postId);
		return LikedPostDto.toDto(likedPost);
	};
	
	// 좋아요 정보 저장
	@Transactional
	public void savePostLiked(LikedPostDto likedPostDto) {
		log.info("LikedPostService의 savePostLiked()");
		// 유저 번호 없으면 저장 못함
		Users users = usersRepository.findById(likedPostDto.getUserNum())
				.orElseThrow(() -> new IllegalArgumentException("좋아요 정보 저장 실패! 대상 유저가 없습니다."));
		// 게시글 번호 없으면 저장 못함
		Post post = postRepository.findById(likedPostDto.getPostId())
				.orElseThrow(() -> new IllegalArgumentException("좋아요 정보 저장 실패! 대상 게시글이 없습니다."));
		// 엔티티를 dto로
		LikedPost likedPost = LikedPost.toEntity(likedPostDto, users, post);
		// 정보 저장하기
		likedPostRepository.save(likedPost);
	};
	
	// 좋아요 정보 삭제
	@Transactional
	public void deletePostLike(Long likedPostId) {
		log.info("LikedPostService의 deletePostLike()");
		// 좋아요 번호 없으면 삭제 못함
		likedPostRepository.findById(likedPostId).orElseThrow(() -> new IllegalArgumentException("좋아요 정보 삭제 실패! 대상 좋아요 정보가 없습니다."));
		// 좋아요 정보 삭제
		likedPostRepository.deleteById(likedPostId);
	};
	
};
