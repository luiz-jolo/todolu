package com.example.todolu.domain.comment.dto;

import com.example.todolu.domain.taskcard.TaskCard;
import com.example.todolu.domain.user.User;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateCommentData(
        @NotBlank
        String description,
        @NotBlank
        String taskCardId) {
}
