package com.example.springboot_project.repository;

import com.example.springboot_project.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
