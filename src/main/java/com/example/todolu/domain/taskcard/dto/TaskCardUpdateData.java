package com.example.todolu.domain.taskcard.dto;

import com.example.todolu.domain.taskcard.TaskCardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskCardUpdateData(
        @NotNull
        Long id,
        @NotBlank
        String title,
        String description,
        LocalDateTime updatedDate,
        LocalDateTime dueDate,
        TaskCardStatus status,
        String priority
) {
}
