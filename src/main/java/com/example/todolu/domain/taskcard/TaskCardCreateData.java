package com.example.todolu.domain.taskcard;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TaskCardCreateData(
        @NotBlank
        String title,
        String description,
        LocalDateTime dueDate,
        String priority
) {

}
