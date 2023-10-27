package com.example.TravelProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TravelProject.dto.CommentsDto;
import com.example.TravelProject.entity.Comments;
import com.example.TravelProject.entity.Post;
import com.example.TravelProject.entity.Users;
import com.example.TravelProject.repository.CommentsRepository;
import com.example.TravelProject.repository.PostRepository;
import com.example.TravelProject.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentsService {
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentsRepository commentsRepository;
	
	// 댓글 저장
	public void saveComment(CommentsDto commentsDto) {
		log.info("CommentsService의 saveComment()");
		// 유저 번호 없으면 저장 못함
		Users user = usersRepository.findById(commentsDto.getUserNum())
				.orElseThrow(() -> new IllegalArgumentException("댓글 저장 실패! 대상 유저가 없습니다."));
		// 게시글 번호 없으면 저장 못함
		Post post = postRepository.findById(commentsDto.getPostId())
				.orElseThrow(() -> new IllegalArgumentException("댓글 저장 실패! 대상 게시글이 없습니다."));
		// 엔티티로 변환
		Comments comments = Comments.toEntity(commentsDto, user, post);
		// 저장
		commentsRepository.save(comments);
	}
	
	// 댓글 가져오기
	public List<CommentsDto> findCommentsByPostId(Long postId) {
		log.info("CommentsService의 findCommentsByPostId()");
		int commentNum = commentsRepository.selectAllComments(postId);
		// 댓글이 있으면 찾고 없으면 null 리턴
		return commentNum != 0 ? 
				commentsRepository.findCommentsByPostId(postId).stream()
				.map(comments -> CommentsDto.toDto(comments)).collect(Collectors.toList()) 
				: null;
	}
	
	// 댓글 삭제하기(삭제로 업데이트)
	public void deleteComment(CommentsDto commentsDto) {
		log.info("CommentsService의 deleteComment()");
		// 댓글 번호 없으면 삭제 못함
		Comments comments = commentsRepository.findById(commentsDto.getCommentId())
				.orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없습니다."));
		comments.setCommentDel(commentsDto.getCommentDel());
		// 저장
		commentsRepository.save(comments);
	}
	
	// 게시글의 전체 댓글 수 가져오기
	public int selectAllComments(Long postId) {
		log.info("CommentsService의 selectAllComments()");
		int commentNum = commentsRepository.selectAllComments(postId);
		return commentNum;
	};
	
};
