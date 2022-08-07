package com.example.springboot_project.controller;

import com.example.springboot_project.dto.ArticleForm;
import com.example.springboot_project.dto.CommentsDto;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import com.example.springboot_project.service.CommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        log.info(form.toString());

        // 1. Convert form to entity
        Article article = form.toEntity();
        log.info("create entity " + article.toString());

        // 2. Let Repository save article entity in DB
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {

        // 1. find article entity from Repository with id, find comments DTO
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentsDto> commentsDtos = commentsService.comments(id);

        // 2. register article and comments DTO in a model
        model.addAttribute("article", articleEntity);
        model.addAttribute("comments", commentsDtos);

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

    @PostMapping("/articles/update")
    public String update(ArticleForm form, Model model) {

        log.info("Update form " + form.toString());

        // 1. convert DTO to Entity
        Article articleEntity = form.toEntity();
        log.info("Update article entity " + articleEntity.toString());

        // 2. find the data from DB by id
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 3. update the data in DB via JPA
        if(target != null) {
            articleRepository.save(articleEntity);
        }

        // 4. set a view
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {

        // 1. find the data from DB by id
        Article target = articleRepository.findById(id).orElse(null);

        // 2. delete the data using Repository
        if(target != null) {
            articleRepository.delete(target);
        }

        // 3. add alerting message
        String message = "The article has been deleted!";
        rttr.addFlashAttribute( "msg", message);

        // 4. set a view
        return "redirect:/articles";
    }

}