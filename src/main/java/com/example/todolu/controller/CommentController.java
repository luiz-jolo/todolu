package com.example.todolu.controller;

import com.example.todolu.domain.comment.CommentService;
import com.example.todolu.domain.comment.dto.CommentDetailData;
import com.example.todolu.domain.comment.dto.CreateCommentData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateCommentData createCommentData, UriComponentsBuilder uriComponentsBuilder){

        var comment = commentService.create(createCommentData);
        var uri = uriComponentsBuilder.path("/comment/{id}").buildAndExpand(comment.getId()).toUri();

        return ResponseEntity.created(uri).body(new CommentDetailData(comment.getDescription()));

    }

}
