package com.example.springboot_project.dto;

import com.example.springboot_project.entity.Article;
import com.example.springboot_project.entity.Comments;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentsDto {

    private Long id;

    private Long article_id;

    private String username;

    private String body;

    public static CommentsDto create(Comments comments) {
        return new CommentsDto(comments.getId(), comments.getArticle().getId(), comments.getUsername(), comments.getBody());
    }

}
