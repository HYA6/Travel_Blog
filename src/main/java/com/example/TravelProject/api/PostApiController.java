package com.example.TravelProject.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.TravelProject.dto.PostImagesDto;
import com.example.TravelProject.service.BlogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PostApiController {
	
	// 게시글 저장
	@RequestMapping("/api/uploadFile")
	public String uploadFile(@RequestParam("files") MultipartFile[] files, Model model) {
		log.info("PostApiController의 uploadFile() 메소드 실행"); 
		
//		String rootUploadDir = "C:" + File.separator + "Upload"; // C:\Upload
//		File dir = new File(rootUploadDir + File.separator + "testfile"); // C:\Upload\testfile
//		
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//		
//		// 업로드되는 파일 정보 수집(2개: file1, file2)
//		Iterator<String> iterator = files.getFileNames();
//		String uploadFilename = "";
//		MultipartFile multipartFile = null;
//		String originalName = "";
//		List<PostImagesDto> list = new ArrayList<>();
		
//		for (MultipartFile file : files) {			
//			PostImagesDto dto = new PostImagesDto(file.getOriginalFilename(), file.getContentType());	
//			list.add(dto);
//			File newFileName = new File(dto.getFileName());
//	        
//			try {
//				file.transferTo(newFileName);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		return "테스트 성공";
	};
	
};
