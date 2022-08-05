package com.example.springboot_project.repository;

import com.example.springboot_project.entity.Article;
import com.example.springboot_project.entity.Comments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentsRepositoryTest {

    @Autowired
    private CommentsRepository commentsRepository;

    @Test
    void findByArticleId() {
        // expected
        Long article_id = 4L;
        Article article = new Article(4L, "Movie", "Leave comments");
        Comments comment1 = new Comments(1L, article, "Tom", "Top gun");
        Comments comment2 = new Comments(2L, article, "Hank", "Interstellar");
        Comments comment3 = new Comments(3L, article, "Tony", "Terminator");
        List<Comments> commentsList = new ArrayList<>();
        commentsList.add(comment1);
        commentsList.add(comment2);
        commentsList.add(comment3);

        // actual
        List<Comments> actualCommentsList = commentsRepository.findByArticleId(article_id);

        // compare
        assertEquals(commentsList.toString(), actualCommentsList.toString());
    }

    @Test
    void findByUsername() {
    }
}