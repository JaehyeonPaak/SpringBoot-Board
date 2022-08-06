package com.example.springboot_project.service;

import com.example.springboot_project.dto.CommentsDto;
import com.example.springboot_project.entity.Article;
import com.example.springboot_project.entity.Comments;
import com.example.springboot_project.repository.ArticleRepository;
import com.example.springboot_project.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentsDto> comments(Long article_id) {
        List<Comments> comments = commentsRepository.findByArticleId(article_id);
        List<CommentsDto> commentsDtos = new ArrayList<>();
        for (Comments e : comments) {
            commentsDtos.add(CommentsDto.create(e));
        }
        return commentsDtos;
    }

    public CommentsDto create(Long article_id, CommentsDto commentsDto) {
        // find the article by article_id
        Article article = articleRepository.findById(article_id).orElse(null);
        // create comments entity
        Comments comments = Comments.create(article, commentsDto);
        // save comments entity into DB
        Comments target = commentsRepository.save(comments);
        // convert comments entity to DTO and return
        return CommentsDto.create(comments);
    }

    public CommentsDto update(Long id, CommentsDto dto) {
        // find target from comments DB by id
        Comments target = commentsRepository.findById(id).orElse(null);
        if(target != null) {
            if((target.getArticle().getId() == dto.getArticle_id()) && (id == dto.getId())) {
                // modify target with comments DTO
                target.put(dto);
                // create comments entity and save into DB
                Comments comments = commentsRepository.save(target);
                // convert created comments entity into comments and return
                return CommentsDto.create(comments);
            }
        }
        return null;
    }

    public CommentsDto delete(Long id) {
        // find target from comments DB by id
        Comments target = commentsRepository.findById(id).orElse(null);
        if(target != null) {
            commentsRepository.delete(target);
            return CommentsDto.create(target);
        }
        return null;
    }
}
