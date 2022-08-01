package com.example.springboot_project.controller;

import com.example.springboot_project.dto.ArticleForm;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        log.info(form.toString());

        // 1. Convert from to entity
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Let Repository save entity in DB
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "";
    }
}
