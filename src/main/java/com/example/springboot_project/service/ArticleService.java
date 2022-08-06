package com.example.springboot_project.service;

import com.example.springboot_project.dto.ArticleDto;
import com.example.springboot_project.dto.ArticleForm;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleDto> index() {
        // List of article entity
        List<Article> articles = (List<Article>) articleRepository.findAll();
        // List of article dto
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article e : articles) {
            articleDtos.add(ArticleDto.create(e));
        }
        return articleDtos;
    }

    public ArticleDto show(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        ArticleDto articleDto = ArticleDto.create(article);
        return articleDto;
    }

    public ArticleDto create(ArticleDto dto) {
        Article article = articleRepository.save(dto.toEntity());
        ArticleDto articleDto = ArticleDto.create(article);
        if (dto.getId() != null) {
            return null;
        }
        return articleDto;
    }

    public ArticleDto update(Long id, ArticleDto dto) {
        Article target = articleRepository.findById(id).orElse(null);
        if(dto != null) {
            Article articleEntity = dto.toEntity();
            if(target != null && articleEntity.getId() == dto.getId()) {
                target.put(articleEntity);
                Article article = articleRepository.save(target);
                return ArticleDto.create(article);
            }
        }
        return null;
    }

    public ArticleDto delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null) {
            articleRepository.delete(target);
            return ArticleDto.create(target);
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
