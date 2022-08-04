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
        Article article1 = new Article(1L, "Billie eilish", "Happier than ever");
        Article article2 = new Article(2L, "Lana del rey", "Chemtrails over the country club");
        Article article3 = new Article(3L, "Lady gaga", "Hold my hand");
        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        // when
        List<Article> articleList = articleService.index();

        // then
        assertEquals(articles.toString(), articleList.toString());
    }

    @Test
    void show_success_idExist() {
        //given
        Long id = 2L;
        Article article = new Article(id, "Lana del rey", "Chemtrails over the country club");

        //when
        Article target = articleService.show(id);

        //then
        assertEquals(article.toString(), target.toString());
    }

    @Test
    void show_failed() {

    }
}