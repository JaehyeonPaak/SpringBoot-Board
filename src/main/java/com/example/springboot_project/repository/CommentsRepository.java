package com.example.springboot_project.repository;

import com.example.springboot_project.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    // find all comment of certain article
    List<Comments> findByArticleId(@Param("article_id") Long article_id);

    // find all comment of certain username
    List<Comments> findByUsername(String username);
}
