package com.example.todolu.domain.comment;

import com.example.todolu.domain.comment.dto.CreateCommentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment create(CreateCommentData createCommentData) {

        var comment = new Comment(createCommentData.description(), createCommentData.user(), createCommentData.taskCard());
        return commentRepository.save(comment);

    }

}
