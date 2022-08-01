package com.example.springboot_project.dto;

import com.example.springboot_project.entity.Article;

public class ArticleForm {

    private String title;
    private String content;


    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
