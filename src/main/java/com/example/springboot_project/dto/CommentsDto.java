package com.example.springboot_project.dto;

import com.example.springboot_project.entity.Article;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentsDto {

    private Long id;

    private Article article;

    private String username;

    private String body;
}
