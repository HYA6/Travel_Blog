package com.example.TravelProject.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.TravelProject.dto.PostContentsDto;
import com.example.TravelProject.dto.PostDto;
import com.example.TravelProject.dto.PostImagesDto;
import com.example.TravelProject.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PostApiController {
	
	@Autowired
	private PostService postService;
	
	// 게시글 대표 이미지 업로드
	@ResponseBody
	@RequestMapping("/api/uploadThumbnail")
	public String uploadThumbnail(@RequestParam("thumbnailFile") MultipartFile file) throws IllegalStateException, IOException {
		log.info("PostApiController의 uploadThumbnail() 메소드 실행"); 
		// 대표 이미지 업로드 처리 부분
		// FileSystemView: 파일 시스템과 디렉토리 정보를 제공
		// getFileSystemView(): 파일 시스템 보기를 반환
		// getHomeDirectory(): 홈 디렉터리를 반환합니다.
//		String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//		log.info("rootPath: {}", rootPath);
		String basePath = "C:/travelBlog/postThumbnail";
//		log.info("basePath: {}", basePath);
		File dir = new File(basePath); // C:\travelBlog\postThumbnail
		// 업로드 디렉토리가 존재하지 않을 경우 업로드 디렉토리를 만든다.
//		log.info("{}", dir.exists());
		if (!dir.exists()) {
			dir.mkdirs();
		};
		String originalName = file.getOriginalFilename();
        String filePath = basePath + "/" + originalName;
		File dest = new File(filePath);
		file.transferTo(dest);
		
		return filePath;
	};
	
	// 게시글 저장
	@ResponseBody
	@RequestMapping("/api/createPost")
	public String createPost(PostDto postDto) throws IllegalStateException, IOException {
		log.info("PostApiController의 createPost() 메소드 실행"); 
		// 오늘 날짜를 작성일에 저장
		Date nowDate = new Date();
		postDto.setPostWrite(nowDate);
		log.info("postDto: {}", postDto);
		// 저장
		postService.createPost(postDto);
		// 저장한 게시글 얻어오기
		PostDto search = postService.selectPostByOption(postDto);
		
		// 저장한 게시글의 고유 번호 리턴
		return search.getPostId() + "";
	};
	
	// 이미지 업로드
	@ResponseBody
	@RequestMapping("/api/uploadImage")
	public List<String> uploadImage(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException {
		log.info("PostApiController의 uploadImage() 메소드 실행"); 
		String basePath = "C:/travelBlog/postImages";
//		log.info("basePath: {}", basePath);
		File dir = new File(basePath); // C:\travelBlog\postImages
		// 업로드 디렉토리가 존재하지 않을 경우 업로드 디렉토리를 만든다.
//		log.info("{}", dir.exists());
		if (!dir.exists()) {
			dir.mkdirs();
		};
		List<String> filePathList = new ArrayList<String>();
	    // 파일 업로드(여러개) 처리 부분
	    for(MultipartFile file : files) {
	        String originalName = file.getOriginalFilename();
	        String filePath = basePath + "/" + originalName;
	        File dest = new File(filePath);
	        file.transferTo(dest);
	        filePathList.add(filePath);
	    };
		return filePathList;
	};
	
	// 이미지 저장
	@ResponseBody
	@RequestMapping("/api/saveImage")
	public String saveImage(PostImagesDto postImagesDto) {
		log.info("PostApiController의 saveImage() 메소드 실행"); 
		log.info("postImagesDto: {}", postImagesDto);
		
		return "테스트 성공";
	};
	
	// 내용 저장
	@ResponseBody
	@RequestMapping("/api/saveContent")
	public String saveContent(PostContentsDto postContentsDto) {
		log.info("PostApiController의 saveContent() 메소드 실행"); 
		log.info("postContentsDto: {}", postContentsDto);
		
		return "테스트 성공";
	};
	
};
