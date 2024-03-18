package com.estsoft.blogproject.repository;

import com.estsoft.blogproject.domain.Article;
import com.estsoft.blogproject.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BlogRepository {
    private final ArticleMapper articleMapper;

    public BlogRepository(ArticleMapper articleMapper){
        this.articleMapper= articleMapper;
    }

    public List<Article> selectArticle(){
        return articleMapper.selectAllArticles();
    }

    public Article findById(long id){
        return articleMapper.selectById(id);
    }

    public int insertOneArticle(Article article){
        return articleMapper.insertOneArticle(article);
    }

    public int updateOneArticle(Article article){
        return articleMapper.updateOneArticle(article);
    }

    public int deleteOneArticle(Long id){
        return articleMapper.deleteOneArticle(id);
    }

    public List<Comment> findComments(Long article_id){
        return articleMapper.findComments(article_id);
    }
    public Comment findComment(Long id){
        return articleMapper.findComment(id);
    }
    public int insertComment(Comment comment){
        return articleMapper.insertComment(comment);
    }
    public int deleteComment(Long id){
        return articleMapper.deleteComment(id);
    }
    public int updateComment(Comment comment){
        return articleMapper.updateComment(comment);
    }
}
