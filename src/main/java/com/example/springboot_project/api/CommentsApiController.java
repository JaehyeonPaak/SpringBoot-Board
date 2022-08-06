package com.example.springboot_project.api;

import com.example.springboot_project.dto.CommentsDto;
import com.example.springboot_project.entity.Comments;
import com.example.springboot_project.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentsApiController {

    @Autowired
    private CommentsService commentsService;

//    // GET
//    @GetMapping("/api/articles/{article_id}/comments")
//    public ResponseEntity<List<CommentsDto>> comments(Long article_id) {
//        List<Comments> commentsDto = commentsService.comments(article_id);
//        return ResponseEntity.status(HttpStatus.OK).body(commentsDto);
//    }

    // POST
    // PUT
    // DELETE
}
