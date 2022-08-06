package com.example.springboot_project.dto;

import com.example.springboot_project.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ArticleDto {

    private Long id;
    private String title;
    private String content;

    public static ArticleDto create(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }

    public Article toEntity() {
        return new Article(id, title, content, null);
    }
}
