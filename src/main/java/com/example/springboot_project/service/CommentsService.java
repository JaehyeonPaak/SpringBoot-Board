package com.example.springboot_project.service;

import com.example.springboot_project.entity.Article;
import com.example.springboot_project.entity.Comments;
import com.example.springboot_project.repository.ArticleRepository;
import com.example.springboot_project.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    //    @Autowired
    //    private ArticleRepository articleRepository;
    public List<Comments> comments(Long article_id) {
        return commentsRepository.findByArticleId(article_id);
    }

}
