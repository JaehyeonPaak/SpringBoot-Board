package com.example.springboot_project.service;

import com.example.springboot_project.dto.ArticleDto;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Article> create(List<ArticleDto> dtos) {
        List<Article> articles = new ArrayList<>();
        for (ArticleDto articleDto : dtos) {
            Article article = articleDto.toEntity();
            articleRepository.save(article);
            articles.add(article);
            //if JSON contains ID property, return error
            if (articleDto.getId() != null) {
                return null;
            }
        }
        return articles;
    }

    public Article update(Long id, ArticleDto dto) {
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

//    public List<Article> createList(List<ArticleDto> dtos) {
//        List<Article> articles = new ArrayList<>();
//        for (ArticleDto articleDto : dtos) {
//            Article article = articleDto.toEntity();
//            articleRepository.save(article);
//            articles.add(article);
//        }
//        articleRepository.findById((long) -1).orElseThrow(() -> new IllegalArgumentException("Fail"));
//        return articles;
//    }
}
