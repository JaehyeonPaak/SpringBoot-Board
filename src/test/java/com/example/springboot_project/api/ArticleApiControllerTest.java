package com.example.springboot_project.api;

import com.example.springboot_project.dto.ArticleDto;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
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
        ArticleDto article1 = new ArticleDto(1L, "Billie eilish", "Happier than ever");
        ArticleDto article2 = new ArticleDto(2L, "Lana del rey", "Bel air");
        ArticleDto article3 = new ArticleDto(3L, "Anderson paak", "Leave the door open");
        List<ArticleDto> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        // actual
        List<ArticleDto> articleList = articleService.index();

        // compare
        assertEquals(articles.toString(), articleList.toString());
    }

    @Test
    void show_success_idExist() {
        // expected
        Long id = 2L;
        ArticleDto articleDto = new ArticleDto(id, "Lana del rey", "Bel air");

        // actual
        ArticleDto target = articleService.show(id);

        // compare
        assertEquals(articleDto.toString(), target.toString());
    }

    @Test
    void show_failed_idNotExist() {
        // expected
        Long id = -1L;
        ArticleDto articleDto = null;

        // actual
        ArticleDto target = articleService.show(id);

        // compare
        assertEquals(articleDto, target);
    }

    @Test
    @Transactional
    void create_success_withoutId() {
        // expected
        String title = "Sarah";
        String content = "Angel";
        ArticleDto articleDto = new ArticleDto(null, title, content);
        Article expected = new Article(4L, title, content);

        // actual
        ArticleDto target = articleService.create(articleDto);

        // compare
        assertEquals(expected.toString(), target.toString());
    }

    @Test
    @Transactional
    void create_success_withId() {
        // expected
        Long id = 4L;
        String title = "Sarah";
        String content = "Angel";
        ArticleDto articleDto = new ArticleDto(id, title, content);
        Article expected = null;

        // actual
        ArticleDto target = articleService.create(articleDto);

        // compare
        assertEquals(expected, target);
    }

    @Test
    @Transactional
    void update_success_withoutId() {
        // expected
        Long id = 1L;
        String title = "Lana del rey";
        String content = "Chemtrails over the country club";
        ArticleDto articleDto = new ArticleDto(null, title, content);
        ArticleDto expected = new ArticleDto(id, title, content);

        // actual
        ArticleDto target = articleService.update(id, articleDto);

        // compare
        assertEquals(expected.toString(), target.toString());
    }

    @Test
    @Transactional
    void update_failed_withNonExistId() {
        //expected
        Long id = 4L;
        String title = "Lana del rey";
        String content = "Chemtrails over the country club";
        ArticleDto articleDto = new ArticleDto(null, title, content);
        ArticleDto expected = null;

        // actual
        ArticleDto target = articleService.update(id, articleDto);

        // compare
        assertEquals(expected, target);
    }

    @Test
    @Transactional
    void update_failed_withoutDto() {
        // expected
        Long id = 1L;
        ArticleDto articleDto = null;
        ArticleDto expected = null;

        // actual
        ArticleDto target = articleService.update(id, articleDto);

        // compare
        assertEquals(expected, target);
    }

    @Test
    @Transactional
    void delete_success_withIdOnUrl() {
        // expected
        Long id = 1L;
        String title = "Billie eilish";
        String content = "Happier than ever";
        ArticleDto expected = new ArticleDto(id, title, content);

        // actual
        ArticleDto target = articleService.delete(id);

        // compare
        assertEquals(expected.toString(), target.toString());
    }

    @Test
    @Transactional
    void delete_success_withNonExistIdOnUrl() {
        // expected
        Long id = 4L;
        ArticleDto expected = null;

        // actual
        ArticleDto target = articleService.delete(id);

        // compare
        assertEquals(expected, target);
    }
}