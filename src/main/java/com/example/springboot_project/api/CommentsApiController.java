package com.example.springboot_project.api;

import com.example.springboot_project.dto.CommentsDto;
import com.example.springboot_project.entity.Comments;
import com.example.springboot_project.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
public class CommentsApiController {

    @Autowired
    private CommentsService commentsService;

    // GET
    @GetMapping("/api/articles/comments/{article_id}")
    public ResponseEntity<List<CommentsDto>> comments(@PathVariable Long article_id) {
        List<CommentsDto> commentsDtos = commentsService.comments(article_id);
        return ResponseEntity.status(HttpStatus.OK).body(commentsDtos);
    }

    // POST
    @PostMapping("/api/articles/comments/{article_id}")
    public ResponseEntity<CommentsDto> create(@PathVariable Long article_id, @RequestBody CommentsDto dto) {
        CommentsDto commentsDto = commentsService.create(article_id, dto);
        return (commentsDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(commentsDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // PUT
    // DELETE
}
