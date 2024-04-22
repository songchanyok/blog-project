package com.estsoft.blogproject.domain;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    private Long id;
    private String title;
    private String content;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    public Article(Long id, String title, String content, LocalDateTime now) {
        this.id=id;
        this.title=title;
        this.content=content;
        this.updateTime=now;
    }

    @Builder
    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }


    public ArticleResponse toResponse(){
        return ArticleResponse.builder().title(title).content(content).build();
    }
}
