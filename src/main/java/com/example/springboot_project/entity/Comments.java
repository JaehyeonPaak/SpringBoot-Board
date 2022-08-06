package com.example.springboot_project.entity;

import com.example.springboot_project.dto.CommentsDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @Column
    private String username;

    @Column
    private String body;

    public static Comments create(Article article, CommentsDto commentsDto) {
        return new Comments(commentsDto.getId(), article, commentsDto.getUsername(), commentsDto.getBody());
    }
}
