package com.example.todolu.domain.comment.dto;

import com.example.todolu.domain.taskcard.TaskCard;
import com.example.todolu.domain.user.User;

import java.time.LocalDateTime;

public record CreateCommentData(String description, User user, TaskCard taskCard) {
}
