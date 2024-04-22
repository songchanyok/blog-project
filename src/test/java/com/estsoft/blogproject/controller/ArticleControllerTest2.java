package com.estsoft.blogproject.controller;

import com.estsoft.blogproject.domain.Article;
import com.estsoft.blogproject.service.SelectArticleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ArticleControllerTest2 {
    @Mock
    SelectArticleService articleService;

    @InjectMocks
    ArticleController articleController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @DisplayName("아티클 세이브")
    @Test
    void testAddArticle() throws Exception {
        Article request = new Article("제목","내용");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(request);

        Mockito.doReturn(1)//new Article(request.getTitle(),request.getContent())
                        .when(articleService).insertOneArticle(any(Article.class));

        ResultActions resultActions = mockMvc.perform(post("/articles/insert").content(requestJson).contentType(MediaType.APPLICATION_JSON));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(request.getTitle()))
                .andExpect(jsonPath("content").value(request.getContent()));
    }

    @DisplayName("블로그 글 전체 목록 조회")
    @Test
    void testSelectArticles() throws Exception {
        // given
        List<Article> list = List.of(new Article(123L,"mockTitle","mockContent", LocalDateTime.now(),LocalDateTime.now()));
        Mockito.doReturn(list)
                .when(articleService).selectArticle();
        // when
        ResultActions resultActions = mockMvc.perform(get("/articles"));

        // then
//        resultActions.andExpect()
    }
}