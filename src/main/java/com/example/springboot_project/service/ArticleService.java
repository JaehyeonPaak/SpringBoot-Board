package com.example.springboot_project.service;

import com.example.springboot_project.dto.ArticleForm;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return (List<Article>) articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (dto.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article target = articleRepository.findById(id).orElse(null);
        Article articleEntity = dto.toEntity();
        if(target != null && articleEntity.getId() == dto.getId()) {
            target.put(articleEntity);
            return articleRepository.save(target);
        }
        return null;
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null) {
            articleRepository.delete(target);
            return target;
        }
        return null;
    }

//    public List<Article> createList(List<ArticleForm> dtos) {
//        List<Article> articles = new ArrayList<>();
//        for (ArticleForm articleForm : dtos) {
//            Article article = articleForm.toEntity();
//            articleRepository.save(article);
//            articles.add(article);
//        }
//        articleRepository.findById((long) -1).orElseThrow(() -> new IllegalArgumentException("Fail"));
//        return articles;
//    }
}
