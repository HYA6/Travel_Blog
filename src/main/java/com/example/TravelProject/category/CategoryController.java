package com.example.TravelProject.category;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

    // 카테고리 유무 확인
    @GetMapping("/categoryChk")
    public String categoryChk(Model model, HttpSession session) {
        log.info("CategoryController의 categoryChk() 메소드");
        Long blogId = (Long) session.getAttribute("blogId");
        List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogId);
        // 카테고리가 없으면 카테고리 생성 페이지로 이동
        if (categoryDto == null || categoryDto.isEmpty()) {
            return "redirect:category";
        } else {
            return "redirect:main";
        }
    };
	
	// 카테고리 생성 페이지로 이동
	@RequestMapping("/category")
	public String category(Model model, HttpSession session) {
        log.info("CategoryController의 category() 메소드");
		Long userNum = (Long) session.getAttribute("userNum");
		Long blogId = (Long) session.getAttribute("blogId");
		
		model.addAttribute("userNum", userNum);
		model.addAttribute("blogId", blogId);
		return "create/categoryCreate";
	};
	
	// 메인 페이지로 이동
	@RequestMapping("/categoryToMain")
	public String categoryToMain(BlogDto blogDto, HttpSession session) {
		log.info("CategoryController의 categoryToMain() 메소드");
//		log.info("blogDto: {}", blogDto);
		
		Long blogId = blogDto.getBlogId();
		Long userNum = blogDto.getUserNum();
		
		// 메인 페이지로 이동
		session.setAttribute("userNum", userNum);
		session.setAttribute("blogId", blogId);
		return "redirect:main";
	};
	
};
