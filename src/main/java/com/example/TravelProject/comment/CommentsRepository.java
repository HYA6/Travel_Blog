package com.example.TravelProject.comment;

import java.util.List;

import com.example.TravelProject.comment.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    // 게시글 유효 번호를 통해 해당 게시글의 전체 댓글 선택
	@Query(value="SELECT * FROM comments WHERE post_id = :post_id ORDER BY comment_gup, comment_seq",
			nativeQuery = true)
	List<Comments> findCommentsByPostId(Long post_id);

    // 전체 댓글 수
	@Query(value="SELECT COUNT(*) FROM comments WHERE post_id = :post_id", nativeQuery = true)
	int selectAllComments(Long post_id);

    // 블로그 유효 번호를 통해 최신 댓글 선택
	@Query(value="SELECT * FROM comments WHERE blog_id = :blog_id AND comment_del IS NULL ORDER BY comment_id DESC Limit 0, 5",
			nativeQuery = true)
	List<Comments> findCommentsByBlogId(Long blog_id);
	
};
