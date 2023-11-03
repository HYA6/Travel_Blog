package com.example.TravelProject.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.example.TravelProject.entity.Post;

@DataJpaTest
@Transactional
class PostRepositoryTest {
	
	@Autowired
	PostRepository postRepository;
	
	@Test
	@DisplayName("페이징 게시글 조회")
	void testPaging() {
		
		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.ASC, "post_id");
		
		Page<Post> postList = postRepository.selectAllPost(1L, pageable);
		System.out.println("postList: " + postList);
		postList.get().forEach(post -> {
            System.out.println("post: " + post);
        });
		
	};
	
};
