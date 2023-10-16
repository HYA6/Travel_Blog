package com.example.TravelProject.api;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ImageApiController {
	
	@Value("${part1.upload.path}")
    private String uploadPath1; // 게시글 썸네일 이미지
	@Value("${part2.upload.path}")
	private String uploadPath2; // 게시글 내용 이미지
	
	@RequestMapping("/image1/{filename}")
	public ResponseEntity<byte[]> image1(@PathVariable("filename") String fileName){
		log.info("MainApiController의 image1()");
		ResponseEntity<byte[]> result = null;
		try{
			String srcFileName = URLDecoder.decode(fileName,"UTF-8");
			log.info("filename : "+srcFileName);
			File file = new File(uploadPath1 + File.separator + srcFileName);
			log.info("file : "+file);
			HttpHeaders header = new HttpHeaders();
			
			//MIME 타입 처리
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		    
		}catch (Exception e){
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	return result;
	};
	
};
