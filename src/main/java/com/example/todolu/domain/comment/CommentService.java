package com.example.todolu.domain.comment;

import com.example.todolu.domain.comment.dto.CreateCommentData;
import com.example.todolu.domain.taskcard.TaskCardRepository;
import com.example.todolu.domain.user.AuthenticatedUserService;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final TaskCardRepository taskCardRepository;

    public CommentService(CommentRepository commentRepository, AuthenticatedUserService authenticatedUserService, TaskCardRepository taskCardRepository){
        this.commentRepository = commentRepository;
        this.authenticatedUserService = authenticatedUserService;
        this.taskCardRepository = taskCardRepository;
    }

    public Comment create(CreateCommentData createCommentData) {

        var user = authenticatedUserService.getAuthenticatedUser();
        var taskCardId = Long.parseLong(createCommentData.taskCardId());
        var taskCard = taskCardRepository.getReferenceById(taskCardId);
//                .orElseThrow(() -> new Exception("card nao encontrado"));

        var comment = new Comment(createCommentData.description(), user, taskCard);
        return commentRepository.save(comment);

    }

}
