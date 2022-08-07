package com.example.springboot_project.controller;

import com.example.springboot_project.dto.ArticleDto;
import com.example.springboot_project.dto.ArticleForm;
import com.example.springboot_project.dto.CommentsDto;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import com.example.springboot_project.service.ArticleService;
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
    private ArticleService articleService;

    @Autowired
    private CommentsService commentsService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        // convert form into article DTO
        ArticleDto articleDto = ArticleDto.create(form.toEntity());

        // save article DTO into DB through Service
        ArticleDto created = articleService.create(articleDto);

        return "redirect:/articles/" + created.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {

        // find article entity from Repository with id, find comments DTO
        ArticleDto articleDto = articleService.show(id);
        List<CommentsDto> commentsDtos = commentsService.comments(id);

        // register article and comments DTO in a model
        model.addAttribute("article", articleDto);
        model.addAttribute("comments", commentsDtos);

        // set a view page
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        // find all the data through Repository with id
        List<ArticleDto> articleDtos = articleService.index();

        // register the data in a model
        model.addAttribute("articleList", articleDtos);

        // set a view page
        return "articles/index";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        // find article DTO from Service to edit
        ArticleDto articleDto = articleService.show(id);

        // register article DTO in a model
        model.addAttribute("article", articleDto);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form, Model model) {

        // convert form into article DTO
        ArticleDto articleDto = ArticleDto.create(form.toEntity());

        // update original article DTO with new one through Service
        ArticleDto result = articleService.update(articleDto.getId(), articleDto);

        // set a view
        return "redirect:/articles/" + result.getId();
    }

    @GetMapping("/articles/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {

        // delete id matching article DTO through Service
        articleService.delete(id);

        // add alerting message
        rttr.addFlashAttribute( "msg", "The article has been deleted!");

        // set a view
        return "redirect:/articles";
    }

}