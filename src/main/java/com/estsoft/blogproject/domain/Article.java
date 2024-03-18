package com.estsoft.blogproject.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    public ArticleResponse toResponse(){
        return ArticleResponse.builder().title(title).content(content).build();
    }
}
