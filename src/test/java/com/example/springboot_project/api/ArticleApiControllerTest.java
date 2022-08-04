package com.example.springboot_project.api;

import com.example.springboot_project.entity.Article;
import com.example.springboot_project.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ArticleApiControllerTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void index_success() {
        // given
        List<Article> articles = new ArrayList<>();

        // when
        List<Article> articleList = articleService.index();

        // then
        assertEquals(articles.toString(), articleList.toString());
    }

    @Test
    void show_success() {
        //given
        Long id = 1L;
        Article article = new Article(id, "Lana", "Bel Air");

        //when
        Article target = articleService.show(id);

        //then
        assertEquals(article.toString(), target.toString());
    }
}