package com.estsoft.blogproject.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Comment {
    private Long id;
    private String comment;
    private LocalDateTime date;
    private Long article_id;
}
