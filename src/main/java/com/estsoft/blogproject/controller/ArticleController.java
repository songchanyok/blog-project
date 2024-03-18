package com.estsoft.blogproject.controller;

import com.estsoft.blogproject.domain.Article;
import com.estsoft.blogproject.domain.ArticleResponse;
import com.estsoft.blogproject.domain.Comment;
import com.estsoft.blogproject.domain.ModifyArticleRequest;
import com.estsoft.blogproject.service.SelectArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ArticleController {

    private final SelectArticleService selectArticleService;

    public ArticleController(SelectArticleService selectArticleService) {
        this.selectArticleService = selectArticleService;
    }

    @GetMapping("/articles")
    public String selectArticles(Model model){
        List<Article> list = selectArticleService.selectArticle();
        list = list.stream().map(x->new Article(x.getId(),x.getTitle(),x.getContent(),x.getCreateTime(),x.getUpdateTime())).toList();
        model.addAttribute("articles", list);
        return "ArticleList";
    }

    @GetMapping("/article/{id}")
    public String findById(@PathVariable long id, Model model){
        Article article = selectArticleService.findById(id);
        List<Comment> comments = selectArticleService.findComments(id);
        model.addAttribute("article",article);
        model.addAttribute("comments",comments);
        return "Article";
    }

    //등록 api
    @PostMapping(value = "/articles/insert")
    @ResponseBody
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody Article article){
//        Article article = request.mapper();
        int result = selectArticleService.insertOneArticle(article);

        log.info("resist = {}",article);
        return ResponseEntity.status(HttpStatus.OK).body(article.toResponse());

    }

    //수정 api
    @PutMapping(value="articles/{id}")
    public ResponseEntity<Integer> updateArticle(@PathVariable Long id,@RequestBody ModifyArticleRequest modifyArticleRequest){
        int result = selectArticleService.update(id,modifyArticleRequest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/new-article")
    public String push(@RequestParam(value = "id", required = false) Long id,Model model){
        if(id!=null){
            Article article = selectArticleService.findById(id);
            model.addAttribute("article",article);
        }else{
            model.addAttribute("article",new Article());
        }
        return "newArticle";
    }

    @DeleteMapping(value="articles/{id}")
    public ResponseEntity<Void>  deleteArticle(@PathVariable Long id){
        int result = selectArticleService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping(value="comment")
    public ResponseEntity<Comment> inputComment(@RequestBody Comment comment){
        selectArticleService.insertComment(comment);
        return ResponseEntity.ok(comment);
    }
    @DeleteMapping(value="comment/{id}")
    public int deleteComment(@PathVariable Long id){
        return selectArticleService.deleteComment(id);
    }
    @PutMapping(value="comment")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        selectArticleService.updateComment(comment);
        return ResponseEntity.ok(comment);
    }
}
