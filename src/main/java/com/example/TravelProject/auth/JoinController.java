package com.example.TravelProject.auth;

import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.TravelProject.blog.BlogDto;
import com.example.TravelProject.category.CategoryDto;
import com.example.TravelProject.blog.BlogService;
import com.example.TravelProject.category.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class JoinController {

    @Autowired
    private JoinService joinService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	// 아이디가 있는지 확인 후 유저 회원가입 및 로그인
	@RequestMapping("/usersInsert")
	public String usersInsert(HttpSession session, @Valid UsersDto usersDto, BindingResult bindingResult, RedirectAttributes rttr) {
		log.info("JoinController의 usersInsert() 메소드");
		Date nowDate = new Date();

        // 회원가입 전처리 (소셜/일반 구분 등)
        usersDto = joinService.joinChk(session, usersDto);

        // 회원 저장
        usersService.saveUser(usersDto);

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .get(0)
                    .getDefaultMessage();

            rttr.addFlashAttribute("alertMsg", errorMessage);
            return "redirect:/join";
        }

        // 로그인 처리
        UsersDto savedUser = usersService.findByuserId(usersDto.getUserId());
        session.setAttribute("userNum", savedUser.getUserNum());

        // 다음 단계는 블로그 확인
        return "redirect:/blogChk";

//        // 로그인 경로 확인 후 각 요소(컬럼)를 DB 형식에 맞게 변환
//		usersDto = joinService.joinChk(session, usersDto);
//
//		// 이미 있는 유저 고유 번호인지 확인
//		UsersDto userInfo = usersService.findByuserId(usersDto.getUserId());
//		Long userEntityId = null;
//		try {
//			userEntityId = userInfo.getUserNum();
//		} catch (NullPointerException e) { }
//
//		// 저장된 데이터나 유저 고유 번호가 없으면 DB에 새로 저장한다.
//		if (userInfo == null || userEntityId == null) {
//			// 로그인 API로 로그인했을 때 이미 가입된 이메일인지 확인 (회원가입은 회원가입 페이지에서 확인함)
//			UsersDto usersEmailChk = usersService.findByuserEmail(usersDto.getUserEmail());
//			if (usersEmailChk != null) {
//				rttr.addFlashAttribute("msg", "이미 가입된 이메일입니다.");
//				return "redirect:/";
//			}
//			// 유저 정보 저장
//			usersService.saveUser(usersDto);
//			userInfo = usersService.findByuserId(usersDto.getUserId()); // 저장된 유저 정보 가져오기
//			userEntityId = userInfo.getUserNum(); // 유저 고유 번호
//			session.setAttribute("userNum", userEntityId);
//			// 새로 가입한 유저면 블로그 생성 페이지로 이동
//			return "redirect:blog";
//		} else {
//			session.setAttribute("userNum", userEntityId);
//			BlogDto blogDto = blogService.selectBlog(userEntityId);
//			// 이미 가입한 유저지만 블로그가 없으면 블로그 생성 페이지로 이동
//			if (blogDto == null) {
//				return "redirect:blog";
//			} else {
//				session.setAttribute("blogId", blogDto.getBlogId());
//				List<CategoryDto> categoryDto = categoryService.selectCategoryList(blogDto.getBlogId());
//				// 이미 가입한 유저고 블로그는 있지만 카테고리가 없으면 카테고리 생성 페이지로 이동
//				if (categoryDto == null || categoryDto.isEmpty()) {
//					return "redirect:category";
//				} else {
//					return "redirect:main";
//				}
//			}
//		}
	}
}
