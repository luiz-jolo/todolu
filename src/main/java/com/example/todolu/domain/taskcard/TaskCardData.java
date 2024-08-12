package com.example.todolu.domain.taskcard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskCardData(
        @NotBlank
        String title,
        String description,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        LocalDateTime dueDate,
        @NotNull
        TaskCardStatus status,
        String priority
) {

}
