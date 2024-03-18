package com.estsoft.blogproject.repository;


import com.estsoft.blogproject.domain.Article;
import com.estsoft.blogproject.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {

    List<Article> selectAllArticles();

    Article selectById(long id);
    int insertOneArticle(Article article);
    int updateOneArticle(Article article);

    int deleteOneArticle(long id);

    List<Comment> findComments(Long article_id);
    Comment findComment(Long id);
    int updateComment(Comment comment);

    int deleteComment(Long id);

    int insertComment(Comment comment);
}
