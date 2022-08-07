package com.example.springboot_project.entity;

import com.example.springboot_project.dto.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

//    @ToString.Exclude
//    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Comments> comments = new HashSet<>();

    public void put(Article articleEntity) {
        if(articleEntity.title != null) {
            this.title = articleEntity.getTitle();
        }
        if(articleEntity.content != null) {
            this.content = articleEntity.getContent();
        }
    }
}
