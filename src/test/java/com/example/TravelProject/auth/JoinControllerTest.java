package com.example.TravelProject.auth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@WebMvcTest(JoinController.class)
public class JoinControllerTest {

    private MockMvc mockMvc;

    @MockitoBean
    private JoinService joinService;

    @MockitoBean
    private UsersService usersService;

    UsersDto inputDto = new UsersDto();

    @BeforeEach
    public void setup() {
        System.out.println("setup");
        inputDto.setUserId("testuser");
        inputDto.setUserName("테스트");
        inputDto.setUserEmail("test@test.com");
        inputDto.setUserPassword("1234");
        inputDto.setUserBirthday(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void testJoin() {
        System.out.println("testJoin");

//        UsersDto savedDto = new UsersDto();
//        savedDto.setUserNum(1L);
//        savedDto.setUserId("testuser");
//
//        given(joinService.joinChk(any(), any())).willReturn(inputDto);
//        given(usersService.findByuserId("testuser")).willReturn(null);
//        given(usersService.findByuserEmail("test@test.com")).willReturn(null);
//        given(usersService.findByuserId("testuser")).willReturn(savedDto);
//
//        mockMvc.perform(post("/usersInsert")
//                        .param("userId", "testuser")
//                        .param("userName", "테스트")
//                        .param("userEmail", "test@test.com")
//                        .param("userPassword", "1234")
//                        .param("userBirthday", "2000-01-01")
//                )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/blogChk"));
    }

    @AfterEach
    public void teardown() {
        System.out.println("teardown");
    }
}
