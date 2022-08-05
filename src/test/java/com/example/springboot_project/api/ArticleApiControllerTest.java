package com.example.springboot_project.api;

import com.example.springboot_project.dto.ArticleDto;
import com.example.springboot_project.dto.ArticleForm;
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
        // expected
        Article article1 = new Article(1L, "Billie eilish", "Happier than ever");
        Article article2 = new Article(2L, "Lana del rey", "Chemtrails over the country club");
        Article article3 = new Article(3L, "Lady gaga", "Hold my hand");
        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        // actual
        List<Article> articleList = articleService.index();

        // compare
        assertEquals(articles.toString(), articleList.toString());
    }

    @Test
    void show_success_idExist() {
        // expected
        Long id = 2L;
        Article article = new Article(id, "Lana del rey", "Chemtrails over the country club");

        // actual
        Article target = articleService.show(id);

        // compare
        assertEquals(article.toString(), target.toString());
    }

    @Test
    void show_failed_idNotExist() {
        // expected
        Long id = -1L;
        Article article = null;

        // actual
        Article target = articleService.show(id);

        // compare
        assertEquals(article, target);
    }

    @Test
    void create_success_withoutId() {
        // expected
        String title = "Sarah";
        String content = "Angel";
        ArticleDto articleDto = new ArticleDto(null, title, content);
        Article expected = new Article(4L, title, content);

        // actual
        Article target = articleService.create(articleDto);

        // compare
        assertEquals(expected.toString(), target.toString());
    }

    @Test
    void create_success_withId() {
        // expected\
        Long id = 4L;
        String title = "Sarah";
        String content = "Angel";
        ArticleDto articleDto = new ArticleDto(id, title, content);
        Article expected = new Article(id, title, content);

        // actual
        Article target = articleService.create(articleDto);

        // compare
        assertEquals(expected, target);
    }
}