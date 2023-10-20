package com.example.TravelProject.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.TravelProject.dto.PostTextsDto;
import com.example.TravelProject.dto.PostDto;
import com.example.TravelProject.dto.PostImagesDto;
import com.example.TravelProject.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PostApiController {
	
	@Value("${part1.upload.path}")
    private String uploadPath1; // 게시글 썸네일 이미지
	@Value("${part2.upload.path}")
	private String uploadPath2; // 게시글 내용 이미지
	
	@Autowired
	private PostService postService;
	
	// 게시글 대표 이미지 업로드
	@ResponseBody
	@RequestMapping("/api/uploadThumbnail")
	public String uploadThumbnail(@RequestParam("thumbnailFile") MultipartFile file) throws IllegalStateException, IOException {
		log.info("PostApiController의 uploadThumbnail() 메소드 실행"); 
		File dir = new File(uploadPath1); // C:\travelBlog\postThumbnail
		// 업로드 디렉토리가 존재하지 않을 경우 업로드 디렉토리를 만든다.
//		log.info("{}", dir.exists());
		if (!dir.exists()) {
			dir.mkdirs();
		};
		// 실제 파일 이름
		String originalName = file.getOriginalFilename();
		// 밀리초를 연결하여 파일 이름 중복 방지
		String saveName = System.currentTimeMillis() + "_" + originalName;
		String filePath = uploadPath1 + "/" + saveName;
		
		// 이미지 파일이 아니면 저장 막기
		if(file.getContentType().startsWith("image") == false) {
			log.warn("this file is not image type");
			return "N";
		};
		
		Path savePath = Paths.get(filePath);
		file.transferTo(savePath);
		
		return saveName;
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
		File dir = new File(uploadPath2); // C:\travelBlog\postThumbnail
		// 업로드 디렉토리가 존재하지 않을 경우 업로드 디렉토리를 만든다.
//		log.info("{}", dir.exists());
		if (!dir.exists()) {
			dir.mkdirs();
		};
		List<String> fileNameList = new ArrayList<String>();
	    // 파일 업로드(여러개) 처리 부분
	    for(MultipartFile file : files) {
	        String originalName = file.getOriginalFilename();
	     // 밀리초를 연결하여 파일 이름 중복 방지
			String saveName = System.currentTimeMillis() + "_" + originalName;
	        String filePath = uploadPath2 + "/" + saveName;
			Path savePath = Paths.get(filePath);
			file.transferTo(savePath);
			fileNameList.add(saveName);
	    };
		return fileNameList;
	};
	
	// 이미지 저장
	@ResponseBody
	@RequestMapping("/api/saveImage")
	public String saveImage(PostImagesDto postImagesDto) {
		log.info("PostApiController의 saveImage() 메소드 실행"); 
		log.info("postImagesDto: {}", postImagesDto);
		postService.saveImage(postImagesDto);
		return "테스트 성공";
	};
	
	// 내용 저장
	@ResponseBody
	@RequestMapping("/api/saveText")
	public String saveText(PostTextsDto postTextsDto) {
		log.info("PostApiController의 saveText() 메소드 실행"); 
		log.info("postTextsDto: {}", postTextsDto);
		postService.saveText(postTextsDto);
		return "테스트 성공";
	};
	
};
