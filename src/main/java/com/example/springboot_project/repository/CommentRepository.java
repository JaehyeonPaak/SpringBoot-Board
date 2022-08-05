package com.example.springboot_project.repository;

import com.example.springboot_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // find all the comment of certain username
    List<Comment> findByUsername(String username);

    // find all the comment of certain article
    List<Comment> findByArticleId();
}
