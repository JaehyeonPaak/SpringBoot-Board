package com.example.springboot_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void put(Article articleEntity) {
        if(articleEntity.title != null) {
            this.title = articleEntity.getTitle();
        }
        if(articleEntity.content != null) {
            this.content = articleEntity.getContent();
        }
    }
}
