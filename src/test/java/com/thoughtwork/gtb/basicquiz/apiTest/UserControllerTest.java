package com.thoughtwork.gtb.basicquiz.apiTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtwork.gtb.basicquiz.controller.UserController;
import com.thoughtwork.gtb.basicquiz.domain.Education;
import com.thoughtwork.gtb.basicquiz.domain.User;
import com.thoughtwork.gtb.basicquiz.dto.EducationDto;
import com.thoughtwork.gtb.basicquiz.dto.UserDto;
import com.thoughtwork.gtb.basicquiz.exception.UserNotFoundException;
import com.thoughtwork.gtb.basicquiz.service.UserService;
import lombok.Getter;
import net.minidev.json.JSONUtil;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import java.awt.print.Pageable;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    private UserDto mockUser;
    private EducationDto mockEducation;

    @BeforeAll()
    public static void init(){
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    public void setup() {
        mockUser = UserDto.builder()
                .id(1L)
                .age(50L)
                .name("name")
                .description("description")
                .avatar("avatar")
                .build();
        mockEducation = EducationDto.builder()
                .id(1L)
                .year(2000L)
                .title("title")
                .user(mockUser)
                .description("description")
                .build();

        Mockito.reset(userService);
    }

    @Test
    public void should_return_user_if_user_id_is_existed() throws Exception {
        when(userService.findUserById(1L)).thenReturn(mockUser);
        mockMvc.perform(get("/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")));

        verify(userService).findUserById(1L);
    }

    @Test
    public void should_return_err_msg_if_user_id_is_not_existed() throws Exception {
        when(userService.findUserById(2L)).thenThrow(new UserNotFoundException("user is not existed"));
        mockMvc.perform(get("/users/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("user is not existed")))
                .andExpect(jsonPath("$.code", is(404)));

        verify(userService).findUserById(2L);
    }

    @Test
    public void should_return_user_dto_with_id_if_create_user_when_validated() throws Exception {
        User user = User.builder()
                .name("name")
                .age(50L)
                .avatar("http://avatar")
                .description("description")
                .build();
        when(userService.createUser(user)).thenReturn(mockUser);
        MockHttpServletRequestBuilder requestBuilder = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();
        assertThat(response.getStatus(),is(HttpStatus.CREATED.value()));
        verify(userService).createUser(user);
    }

    @Test
    public void should_return_err_msg_if_create_user_when_invalidated() throws Exception {
        User user = User.builder()
                .name("name")
                .age(50L)
                .avatar("err")
                .description("description")
                .build();
        MockHttpServletRequestBuilder requestBuilder = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();
        assertThat(response.getStatus(),is(HttpStatus.BAD_REQUEST.value()));
    }

}
