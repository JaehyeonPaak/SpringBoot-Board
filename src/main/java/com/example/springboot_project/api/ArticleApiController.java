package com.example.springboot_project.api;

import com.example.springboot_project.dto.ArticleDto;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return (List<Article>) articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleDto dto) {
        Article articleEntity = dto.toEntity();
        return articleRepository.save(articleEntity);
    }

    // PATCH
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleDto dto) {
        Article target = articleRepository.findById(id).orElse(null);
        Article articleEntity = dto.toEntity();
        if(target != null && articleEntity.getId() == dto.getId()) {
            target.put(articleEntity);
            articleRepository.save(target);
            return ResponseEntity.status(HttpStatus.OK).body(target);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null) {
            articleRepository.delete(target);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
