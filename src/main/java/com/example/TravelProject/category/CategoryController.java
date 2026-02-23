package com.example.TravelProject.category;

import com.example.TravelProject.blog.BlogService;
import com.example.TravelProject.security.data.CustomUserDetails;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.TravelProject.blog.BlogDto;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Controller
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    // 카테고리 유무 확인
    @GetMapping("/categoryChk")
    public String categoryChk(Model model, HttpSession session, @AuthenticationPrincipal CustomUserDetails customUser) {
        log.info("CategoryController의 categoryChk() 메소드");
        // 로그인한 유저 블로그 정보 가져오기
        BlogDto blogDto = blogService.selectBlog(customUser.getUserNum());
        Long blogId = blogDto.getBlogId();
        // 카테고리 정보 가져오기
        List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogId);
        // 카테고리가 없으면 카테고리 생성 페이지로 이동
        if (categoryDto == null || categoryDto.isEmpty()) {
            return "redirect:category";
        } else {
            return "redirect:main";
        }
    }
	
	// 카테고리 생성 페이지로 이동
	@RequestMapping("/category")
	public String category(Model model, @AuthenticationPrincipal CustomUserDetails customUser) {
        log.info("CategoryController의 category() 메소드");
		Long userNum = customUser.getUserNum();
        BlogDto blogDto = blogService.selectBlog(userNum);
        Long blogId = blogDto.getBlogId();
		
		model.addAttribute("userNum", userNum);
		model.addAttribute("blogId", blogId);
		return "create/categoryCreate";
	}
	
	// 메인 페이지로 이동
	@RequestMapping("/categoryToMain")
	public String categoryToMain() {
		log.info("CategoryController의 categoryToMain() 메소드");
		return "redirect:main";
	}
	
}
