package com.example.springboot_project.api;

import com.example.springboot_project.dto.ArticleDto;
import com.example.springboot_project.service.ArticleService;
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
    private ArticleService articleService;

    // GET
    @GetMapping("/api/articles")
    public List<ArticleDto> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleDto show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleDto> create(@RequestBody ArticleDto dto) {
        ArticleDto articleDto = articleService.create(dto);
        return articleDto != null ?
                ResponseEntity.status(HttpStatus.OK).body(articleDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PUT
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> update(@PathVariable Long id, @RequestBody ArticleDto dto) {
        ArticleDto articleDto = articleService.update(id, dto);
        return (articleDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(articleDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<ArticleDto> delete(@PathVariable Long id) {
        ArticleDto articleDto = articleService.delete(id);
        return (articleDto != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;
    }

//    //Transaction
//    @PostMapping("/api/transaction-test")
//    @Transactional
//    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleDto> dtos) {
//        List<Article> articles = articleService.createList(dtos);
//
//        return (articles != null) ?
//                ResponseEntity.status(HttpStatus.OK).body(articles) :
//                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }

}
