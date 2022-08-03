package com.example.springboot_project.service;

import com.example.springboot_project.dto.ArticleDto;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Article create(ArticleDto dto) {
        Article articleEntity = dto.toEntity();
        return dto.getId() != null ? null : articleRepository.save(articleEntity);
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
}
