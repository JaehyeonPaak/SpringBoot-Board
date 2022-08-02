package com.example.springboot_project.controller;

import com.example.springboot_project.dto.ArticleForm;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

        // 1. Convert form to entity
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. Let Repository save entity in DB
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. find data through Repository with id
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. register the data in a model
        model.addAttribute("article", articleEntity);

        // 3. set a view page
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        // 1. find all the data through Repository with id
        List<Article> articleEntityList = (List<Article>) articleRepository.findAll();

        // 2. register the data in a model
        model.addAttribute("articleList", articleEntityList);

        // 3. set a view page
        return "articles/index";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        // 1. find data to edit from Repository
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. register the data in a model
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

}
