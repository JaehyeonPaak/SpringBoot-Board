package com.example.springboot_project.controller;

import com.example.springboot_project.dto.ArticleForm;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        System.out.println(form.toString());

        // 1. Convert from to entity
        Article article = form.toEntity();
        System.out.println(article.toString());

        // 2. Let Repository save entity in DB
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());

        return "";
    }
}
