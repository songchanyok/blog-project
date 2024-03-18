package com.estsoft.blogproject.service;


import com.estsoft.blogproject.domain.Article;
import com.estsoft.blogproject.domain.Comment;
import com.estsoft.blogproject.domain.ModifyArticleRequest;
import com.estsoft.blogproject.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SelectArticleService {
    private final BlogRepository blogRepository;

    public SelectArticleService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Article> selectArticle() {
        return blogRepository.selectArticle();
    }

    public Article findById(long id) {
        return blogRepository.findById(id);
    }

    public int insertOneArticle(Article article){
        return blogRepository.insertOneArticle(article);
    }

    public int update(long id, ModifyArticleRequest modifyArticleRequest){
        Article article = findById(id);
        String title = article.getTitle();
        String content = article.getContent();

        if(!title.equals(modifyArticleRequest.getTitle())){
            title=modifyArticleRequest.getTitle();
        }

        if(!content.equals(modifyArticleRequest.getContent())){
            content=modifyArticleRequest.getContent();
        }

        Article updatedArticle = new Article(id,title,content,LocalDateTime.now());
        return blogRepository.updateOneArticle(updatedArticle);

    }

    public int delete(Long id){
        return blogRepository.deleteOneArticle(id);
    }

    public List<Comment> findComments(Long id){
        return blogRepository.findComments(id);
    }
    public void insertComment(Comment comment){
        blogRepository.insertComment(comment);
    }

    public int deleteComment(Long id){
        return blogRepository.deleteComment(id);
    }
    public void updateComment(Comment comment){
//        Long id = comment.getId();
//        Comment comment1 = blogRepository.findComment(id);
//
        blogRepository.updateComment(comment);
    }
}
